/**
 * 
 */
package com.pc.bsp.security.po;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.pc.bsp.security.service.IPubUserDetails;

/**
 * @author simple
 *
 */
public class PubUsers implements IPubUserDetails, Serializable{
	
	private static final long serialVersionUID = -261114658103699072L;
	
	//自定义变量
	private String userId;			//用户ID(PK)
	private String userAccount;		//用户账号(登录)
	private String userName;		//用户姓名
	private String userPassword;	//用户密码
	private String userOrg;			//用户所在机构
	private String userDuty;		//用户职位
	private String userDesc;		//用户描述
	private String enable;			//是否可用
	private String isSys;			//是否超级用户
	private String userDepartment;	//用户隶属部门
	@SuppressWarnings({ "unchecked" })
	private Set<PubUsersRoles> pubUsersRoles = new HashSet(0);	//用户角色集
	
	//实现了UserDetails之后的相关变量
    private  String password;
    private  String username;
    private  Set<GrantedAuthority> authorities;
    private  boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean credentialsNonExpired;
    
    /**
     * 默认构造函数
     */
    public PubUsers(){
    	
    }
    
    /**
     * 自定义构造函数
     * @param userId
     * @param userAccount
     * @param userName
     * @param userPassword
     * @param userOrg
     * @param userDuty
     * @param userDesc
     * @param enable
     * @param isSys
     * @param userDepartment
     * @param pubUsersRoles
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public PubUsers(String userId, String userAccount, String userName, String userPassword, String userOrg, 
			String userDuty, String userDesc, String enable, String isSys,String userDepartment, Set<PubUsersRoles> pubUsersRoles,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, 
			Collection<GrantedAuthority> authorities){
    	
    	if (((userAccount == null) || "".equals(userAccount)) || (userPassword == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.userId = userId;
        this.userAccount = userAccount;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userDuty = userDuty;
        this.userOrg = userOrg;
        this.userDesc = userDesc;
        this.enable = enable;
        this.isSys = isSys;
        this.userDepartment = userDepartment;
        this.pubUsersRoles = pubUsersRoles;
        
        this.username = userAccount;
        this.password = userPassword;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    	
    }
    
    /**
     * 授权集合排序
     * @param authorities
     * @return
     */
    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }
    
    /**
     * 权限匹配
     */
    @SuppressWarnings("serial")
	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
    
    /**
     * 自定义权限对比方法
     */
    public boolean equals(Object rhs) {
        if (!(rhs instanceof PubUsers) || (rhs == null)) {
            return false;
        }

        PubUsers user = (PubUsers) rhs;

        //具有的权限。
        if (!authorities.equals(user.authorities)) {
            return false;
        }

        // 通过Spring Security构建一个用户时，用户名和密码不能为空。
        return (this.getPassword().equals(user.getPassword()) && this.getUsername().equals(user.getUsername())
                && (this.isAccountNonExpired() == user.isAccountNonExpired())
                && (this.isAccountNonLocked() == user.isAccountNonLocked())
                && (this.isCredentialsNonExpired() == user.isCredentialsNonExpired())
                && (this.isEnabled() == user.isEnabled()));
    }
    
    public int hashCode() {
        int code = 9792;

        //若该用户不是登录人员，则可以允许没有authorities。
		if (null != getUsername() && null != getAuthorities()) {
			for (GrantedAuthority authority : getAuthorities()) {

				code = code * (authority.hashCode() % 7);
			}
		}

        if (this.getPassword() != null) {
            code = code * (this.getPassword().hashCode() % 7);
        }

        if (this.getUsername() != null) {
            code = code * (this.getUsername().hashCode() % 7);
        }

        if (this.isAccountNonExpired()) {
            code = code * -2;
        }

        if (this.isAccountNonLocked()) {
            code = code * -3;
        }

        if (this.isCredentialsNonExpired()) {
            code = code * -5;
        }

        if (this.isEnabled()) {
            code = code * -7;
        }

        return code;
    }

    /** UserDetails默认的方法 **/
    public String getUsername() {
        return username;
    }
	
	public String getPassword() {
        return password;
    }
	
	public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities( Collection<GrantedAuthority> authorities ){
    	this.authorities = (Set<GrantedAuthority>) authorities;
    }
    
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
    	if(this.enable.equals("1")){
    		return true;
    	}else{
    		return false;
    	}
    }  
	
    /** 自定义方法 **/
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserOrg() {
		return userOrg;
	}
	
	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
	
	public String getUserDesc() {
		return userDesc;
	}
	
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	
	public String getUserDuty() {
		return userDuty;
	}
	
	public void setUserDuty(String userDuty) {
		this.userDuty = userDuty;
	}
	
	public String getEnable() {
		return enable;
	}
	
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public String getIsSys() {
		return isSys;
	}
	
	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	
	public Set<PubUsersRoles> getPubUsersRoles() {
		return pubUsersRoles;
	}
	
	public void setPubUsersRoles(Set<PubUsersRoles> pubUsersRoles) {
		this.pubUsersRoles = pubUsersRoles;
	}
    
    /**
     * PubUsers对象转化为字符串
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("UserAccount: ").append(this.userAccount).append("; ");
        sb.append("UserOrg: ").append(this.userOrg).append("; ");
        sb.append("UserDuty: ").append(this.userDuty).append("; ");
        sb.append("UserDesc: ").append(this.userDesc).append("; ");
        sb.append("IsSys: ").append(this.isSys).append("; ");
        sb.append("Enable: ").append(this.enable).append("; ");
        sb.append("UserDepartment: ").append(this.userDepartment).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("CredentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if ( null !=authorities  && !authorities.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

}
