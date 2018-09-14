-- ----------------------------
-- for oracle
-- ----------------------------

-- ----------------------------
-- Table structure for pub_authorities
-- ----------------------------

CREATE TABLE pub_authorities (
  authority_id varchar2(32) NOT NULL,
  authority_name varchar2(64) NOT NULL,
  authority_desc varchar2(128) DEFAULT NULL,
  enable char(1) NOT NULL,
  is_sys char(1) NOT NULL,
  PRIMARY KEY (authority_id)
) ;

-- ----------------------------
-- Records of pub_authorities
-- ----------------------------
INSERT INTO pub_authorities VALUES ('AUTH_1377925254409','[系统管理]_机构管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1377925320407','[系统管理]_用户管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1377925329991','[系统管理]_角色管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1377925337277','[系统管理]_权限管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1377925344384','[系统管理]_资源管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1377925351603','[系统管理]_菜单管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1385021957879','[系统管理]_参数管理','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1472442396521','权限测试','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1472442424121','文件测试','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1472450956478','测试用例1','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1472450971285','测试用例2','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1472450976416','测试用例3','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1475889178590','[系统默认]','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1475891882706','[系统管理]_数据字典','','1','1');
INSERT INTO pub_authorities VALUES ('AUTH_1475894194473','[系统管理]','','1','1');
commit;


-- ----------------------------
-- Table structure for pub_authorities_resources
-- ----------------------------

CREATE TABLE pub_authorities_resources (
  id varchar2(32) NOT NULL,
  authority_id varchar2(32) NOT NULL,
  resource_id varchar2(32) NOT NULL,
  PRIMARY KEY (id)
) ;

-- ----------------------------
-- Records of pub_authorities_resources
-- ----------------------------
INSERT INTO pub_authorities_resources VALUES ('0099a3b5347e45218817a0ff4dac94c7','AUTH_1377925344384','res1377893518122');
INSERT INTO pub_authorities_resources VALUES ('01f115dfdf22442b8c8661f3889cab6d','AUTH_1472450976416','res1472450926208');
INSERT INTO pub_authorities_resources VALUES ('01f4463eb1124cb1937015f52b94ff8f','AUTH_1377925344384','res1377893560233');
INSERT INTO pub_authorities_resources VALUES ('0289313ed04e40b1a73ee4a660dc625a','AUTH_1377925329991','res1377893281414');
INSERT INTO pub_authorities_resources VALUES ('05853765dcd6444794dc4579f8969e06','AUTH_1377925337277','res1377893378977');
INSERT INTO pub_authorities_resources VALUES ('059ce2ca191a4c5db7f1e457f4853284','AUTH_1385021957879','res1385021795181');
INSERT INTO pub_authorities_resources VALUES ('084056a50ac14262bd3e43206c7c180f','AUTH_1475894194473','res1377893577864');
INSERT INTO pub_authorities_resources VALUES ('165ac84cf1594f6b8f057c49f792e173','AUTH_1475894194473','res1377893378977');
INSERT INTO pub_authorities_resources VALUES ('19ab795194a44274ba83b56346589e40','AUTH_1475894194473','res1377893396054');
INSERT INTO pub_authorities_resources VALUES ('1e6779afaa0f4be9861355a4dd8e24dd','AUTH_1475894194473','res1377893316245');
INSERT INTO pub_authorities_resources VALUES ('1f337c5805a44b7da68c6fadb1d65e69','AUTH_1377925351603','res1377893867720');
INSERT INTO pub_authorities_resources VALUES ('2100b2ac21fe4f61b253c1531eab37f6','AUTH_1475894194473','res1377953618390');
INSERT INTO pub_authorities_resources VALUES ('22c74a64eedd4d63919cbd3e1ebf8080','AUTH_1377925329991','res1377949463024');
INSERT INTO pub_authorities_resources VALUES ('25ea67a4c3304a7abd5ad2be0542ead7','AUTH_1377925320407','res1377948474223');
INSERT INTO pub_authorities_resources VALUES ('2c7f0c058b0e48f0bed76889c20b7a57','AUTH_1475894194473','res1377893560233');
INSERT INTO pub_authorities_resources VALUES ('2e80be7e2b3d41aa91a66de15fa14c86','AUTH_1475894194473','res1377893215540');
INSERT INTO pub_authorities_resources VALUES ('2eb944e1a7d44270bf8cb6a5d92a9c82','AUTH_1377925254409','res1377868722342');
INSERT INTO pub_authorities_resources VALUES ('3070eaa198b34b0b9789c895a9b3eca9','AUTH_1475894194473','res1385022438821');
INSERT INTO pub_authorities_resources VALUES ('351b8926d3a54d41b40d1eecf83a98f8','AUTH_1475891882706','res_1475891864334');
INSERT INTO pub_authorities_resources VALUES ('3753427a38cb440d876429ff28a0505c','AUTH_1475894194473','res1385021651938');
INSERT INTO pub_authorities_resources VALUES ('40ecd640bece4cf3b2eb4f75320c3b5d','AUTH_1385021957879','res1385021651938');
INSERT INTO pub_authorities_resources VALUES ('411feec4d8ef46f098d4266f3f40774d','AUTH_1475894194473','res1377893444246');
INSERT INTO pub_authorities_resources VALUES ('45f0847873c74c7eac4e4b520096fc34','AUTH_1377925254409','res1377877933606');
INSERT INTO pub_authorities_resources VALUES ('465d89a752774c63819fc57771d223f3','AUTH_1475894194473','res_1475891864334');
INSERT INTO pub_authorities_resources VALUES ('47d3928b61fd4fbe8bcc66c2e71c3c06','AUTH_1377925351603','res1377953767306');
INSERT INTO pub_authorities_resources VALUES ('49c84f5e44c74ba4b16d3e63c2d088b2','AUTH_1377925337277','res1377893364765');
INSERT INTO pub_authorities_resources VALUES ('4d6c7b2ca903477fb61c35d2032bafdd','AUTH_1377925320407','res1377893155400');
INSERT INTO pub_authorities_resources VALUES ('4f26c22d4d5c4a1eb9d77cdecb1bd7e2','AUTH_1475894194473','res1377947959118');
INSERT INTO pub_authorities_resources VALUES ('4fc574c4a16a4159bba0d1cf2e8b68c2','AUTH_1377925329991','res1377893296002');
INSERT INTO pub_authorities_resources VALUES ('519c9636e8074d78858478be21940648','AUTH_1475894194473','res1377893896653');
INSERT INTO pub_authorities_resources VALUES ('525df53722df47f2b3c97fab4b3d1565','AUTH_1475894194473','res1377949463024');
INSERT INTO pub_authorities_resources VALUES ('52bba0d87cda4b97bbbda3ea86f896c8','AUTH_1475889178590','res1400830717774');
INSERT INTO pub_authorities_resources VALUES ('583c684a6ce44abf8026cd475830a762','AUTH_1472442424121','res1472441028703');
INSERT INTO pub_authorities_resources VALUES ('58c6f337214f4706ad43a29c776163ef','AUTH_1475894194473','res1377953461190');
INSERT INTO pub_authorities_resources VALUES ('595a1e3b149e42fda08c4933baa92e12','AUTH_1472450956478','res1472450909584');
INSERT INTO pub_authorities_resources VALUES ('5fc7b5884d12498896dd030c976a1421','AUTH_1377925344384','res1377953618390');
INSERT INTO pub_authorities_resources VALUES ('61fdd7a93ace4f24bf04c59428acece3','AUTH_1377925344384','res1377893532098');
INSERT INTO pub_authorities_resources VALUES ('633ddbe974cd4061bdf0cbc36825c2a4','AUTH_1385021957879','res1385021889459');
INSERT INTO pub_authorities_resources VALUES ('643f24ae50284948906a9bf752f85b64','AUTH_1475894194473','res1377893296002');
INSERT INTO pub_authorities_resources VALUES ('6a06c189ffb344178e0e7211665cd0e3','AUTH_1377925337277','res1377953461190');
INSERT INTO pub_authorities_resources VALUES ('6ca4a8e32af44d06adda02eadb8b65ae','AUTH_1475894194473','res1377893170111');
INSERT INTO pub_authorities_resources VALUES ('706a2a3a3e0e413c8b7911afbfb6282c','AUTH_1475894194473','res1377868741129');
INSERT INTO pub_authorities_resources VALUES ('7132b6880be8405d9045ef5d5562fd63','AUTH_1377925337277','res1377893444246');
INSERT INTO pub_authorities_resources VALUES ('74ef4465c6b44596997faa292374211d','AUTH_1475894194473','res1377893503853');
INSERT INTO pub_authorities_resources VALUES ('7b4b5ce0e306442d94082079ed21dff8','AUTH_1475894194473','res1377893518122');
INSERT INTO pub_authorities_resources VALUES ('7e78d1c29177407f9e9077280c4a97a7','AUTH_1377925337277','res1377893396054');
INSERT INTO pub_authorities_resources VALUES ('8683347918ee4e7abf6178eca96a9208','AUTH_1475894194473','res1377893364765');
INSERT INTO pub_authorities_resources VALUES ('90aa3eb2e66d41cc9d7e65b1874f2aeb','AUTH_1377925254409','res1377947996512');
INSERT INTO pub_authorities_resources VALUES ('943c958cb4da4ad6bbc78750478373ae','AUTH_1475894194473','res1377893137228');
INSERT INTO pub_authorities_resources VALUES ('9816c91e7bc04758a65557a4bb37f483','AUTH_1377925320407','res1377893137228');
INSERT INTO pub_authorities_resources VALUES ('9a450020c14c4780afc08abbe8332330','AUTH_1475894194473','res1377893881340');
INSERT INTO pub_authorities_resources VALUES ('9b6efbd3aa514c8eb4b98b65429c4d7e','AUTH_1377925320407','res1377948503153');
INSERT INTO pub_authorities_resources VALUES ('9ded854025244edaa41fa1f956a4810d','AUTH_1377925344384','res1377893503853');
INSERT INTO pub_authorities_resources VALUES ('a0aedfdc2b3d47f3bb32effbe6304ef4','AUTH_1377925351603','res1377893881340');
INSERT INTO pub_authorities_resources VALUES ('a3888ac435a4455dadd73de8bc14e659','AUTH_1475889178590','res1400748438871');
INSERT INTO pub_authorities_resources VALUES ('a48c758fec6841268b38aae18e0457bc','AUTH_1475894194473','res1385021889459');
INSERT INTO pub_authorities_resources VALUES ('a4aafe1c9098415c967e9e78400ebd95','AUTH_1475894194473','res1377947996512');
INSERT INTO pub_authorities_resources VALUES ('a83ce1cef8624663a2bd48cb20858b10','AUTH_1475894194473','res1377953767306');
INSERT INTO pub_authorities_resources VALUES ('a83e4006c550451c9a42ede32f425cac','AUTH_1377925320407','res1377893215540');
INSERT INTO pub_authorities_resources VALUES ('a9900d92c2aa4a83986a88f75c79a5c3','AUTH_1475894194473','res1377893867720');
INSERT INTO pub_authorities_resources VALUES ('b00416ac80264a5b99caff710ec23d70','AUTH_1475894194473','res1377893281414');
INSERT INTO pub_authorities_resources VALUES ('b00eb966a16d4bd09a65b7402795abe3','AUTH_1472442396521','res1472440846167');
INSERT INTO pub_authorities_resources VALUES ('b37b576c994b4be8a72fe65741ebe170','AUTH_1377925320407','res1377893170111');
INSERT INTO pub_authorities_resources VALUES ('b56f34d27f474567a55f44ced2f3369f','AUTH_1475894194473','res1377949502269');
INSERT INTO pub_authorities_resources VALUES ('b67222fc8b9d40d58c96eb080dfdeac8','AUTH_1475894194473','res1377768638475');
INSERT INTO pub_authorities_resources VALUES ('ba2606dea4744e9cac7558a6ba515d24','AUTH_1377925254409','res1377868741129');
INSERT INTO pub_authorities_resources VALUES ('bbce3c6147b44ae8adaf46eaab367180','AUTH_1475894194473','res1377948474223');
INSERT INTO pub_authorities_resources VALUES ('bcf4b06a0fba4542b80b791654df85d3','AUTH_1385021957879','res1385022438821');
INSERT INTO pub_authorities_resources VALUES ('be259ebc3c5946b9afb20b44f14916b0','AUTH_1377925329991','res1377949502269');
INSERT INTO pub_authorities_resources VALUES ('bf53f5de3c1c46be8676ca72c0d0bdbd','AUTH_1377925329991','res1377893316245');
INSERT INTO pub_authorities_resources VALUES ('bf78cf84970a468dbf58b661a7427795','AUTH_1475894194473','res1377877933606');
INSERT INTO pub_authorities_resources VALUES ('c176df6148b449899bed2991dec7de62','AUTH_1475894194473','res1377868722342');
INSERT INTO pub_authorities_resources VALUES ('c458f485a755493ab7249ee834ac510a','AUTH_1377925344384','res1377893577864');
INSERT INTO pub_authorities_resources VALUES ('c51c22a351de4703b11fbdc33149a0b0','AUTH_1377925254409','res1377947959118');
INSERT INTO pub_authorities_resources VALUES ('c5289dda627b4d59bf8095dd7cc594c8','AUTH_1472450971285','res1472450919144');
INSERT INTO pub_authorities_resources VALUES ('cb9558fdf7c64bcbac6f59a24b804dee','AUTH_1475894194473','res1377893266799');
INSERT INTO pub_authorities_resources VALUES ('cd6c5c5ad5204f948d6ef1b086f3b83b','AUTH_1475889178590','res1377937279687');
INSERT INTO pub_authorities_resources VALUES ('d164c779aa8244f7992788cffe5793f4','AUTH_1377925344384','res1377953645526');
INSERT INTO pub_authorities_resources VALUES ('d87ad76f30fe48a3bedc91e4af085dd9','AUTH_1377925351603','res1377893896653');
INSERT INTO pub_authorities_resources VALUES ('dbd375d310034a26a9fd3706077f869b','AUTH_1475894194473','res1385021795181');
INSERT INTO pub_authorities_resources VALUES ('e95a40f23edf470a8629b31c53927f92','AUTH_1475894194473','res1377953535482');
INSERT INTO pub_authorities_resources VALUES ('eab5326c75d2494181ca9f13dd916660','AUTH_1475894194473','res1377893155400');
INSERT INTO pub_authorities_resources VALUES ('ebed278bd78e4d3e8cfd70ac2b0841c2','AUTH_1385021957879','res1385021674643');
INSERT INTO pub_authorities_resources VALUES ('ed8fa737efb74cc299d0a4df108a49dd','AUTH_1475894194473','res1377893532098');
INSERT INTO pub_authorities_resources VALUES ('f1218551138544cba4f15992709af74c','AUTH_1475894194473','res1377948503153');
INSERT INTO pub_authorities_resources VALUES ('f1416ed2026c40838f9c2a90c8e3ff56','AUTH_1377925337277','res1377953535482');
INSERT INTO pub_authorities_resources VALUES ('f1cdb12f5a604dd5a75074a54c898fb2','AUTH_1377925329991','res1377893266799');
INSERT INTO pub_authorities_resources VALUES ('f329844919fb4cab907d798478098aeb','AUTH_1377925254409','res1377768638475');
INSERT INTO pub_authorities_resources VALUES ('fb80f8d1a1bb4d17acb0bbadb2759bae','AUTH_1475894194473','res1385021674643');
INSERT INTO pub_authorities_resources VALUES ('fe3014b6cba64d04b2d785fe22c48a9c','AUTH_1475894194473','res1377953645526');
commit;


-- ----------------------------
-- Table structure for PUB_DICTIONARY
-- ----------------------------
CREATE TABLE PUB_DICTIONARY
(
  ID          VARCHAR2(32),
  PID         VARCHAR2(32)                 NOT NULL,
  DICT_CODE   VARCHAR2(32)                 NOT NULL,
  DICT_DATA   VARCHAR2(32)                 NOT NULL,
  DICT_NAME   VARCHAR2(32)                 NOT NULL,
  DICT_LEVEL  CHAR(1)                      NOT NULL,
  IS_PARENT   CHAR(1)                      DEFAULT 0,
  REMARK      VARCHAR2(128)
);

COMMENT ON TABLE PUB_DICTIONARY IS '字典表';

COMMENT ON COLUMN PUB_DICTIONARY.ID IS '唯一标识';

COMMENT ON COLUMN PUB_DICTIONARY.PID IS '父节点唯一标示';

COMMENT ON COLUMN PUB_DICTIONARY.DICT_CODE IS '字典外部标识';

COMMENT ON COLUMN PUB_DICTIONARY.DICT_DATA IS '字典值';

COMMENT ON COLUMN PUB_DICTIONARY.DICT_NAME IS '字典显示值';

COMMENT ON COLUMN PUB_DICTIONARY.DICT_LEVEL IS '层级';

COMMENT ON COLUMN PUB_DICTIONARY.IS_PARENT IS '是否父节点';

COMMENT ON COLUMN PUB_DICTIONARY.REMARK IS '备注';


CREATE INDEX PUB_DICTIONARY_CODE_INDEX ON PUB_DICTIONARY(DICT_CODE);


CREATE UNIQUE INDEX PUB_DICTIONARY_PK ON PUB_DICTIONARY(ID);


ALTER TABLE PUB_DICTIONARY ADD (
  CONSTRAINT PUB_DICTIONARY_PK
  PRIMARY KEY
  (ID)
  USING INDEX PUB_DICTIONARY_PK);

-- ----------------------------
-- Table structure for pub_menu
-- ----------------------------

CREATE TABLE pub_menu (
  menu_id varchar2(32) NOT NULL,
  menu_name varchar2(64) DEFAULT NULL,
  menu_url varchar2(64) DEFAULT NULL,
  menu_type char(1) DEFAULT NULL,
  menu_pid varchar2(32) DEFAULT NULL,
  menu_desc varchar2(64) DEFAULT NULL,
  open_type char(1) DEFAULT NULL,
  PRIMARY KEY (menu_id)
) ;

-- ----------------------------
-- Records of pub_menu
-- ----------------------------
INSERT INTO pub_menu VALUES ('00','系统菜单','','1','0','00',NULL);
INSERT INTO pub_menu VALUES ('0000','系统管理','','1','00','01',NULL);
INSERT INTO pub_menu VALUES ('000000','机构管理','/org','0','0000','0101','a');
INSERT INTO pub_menu VALUES ('000001','用户管理','/user','0','0000','0102','a');
INSERT INTO pub_menu VALUES ('000002','角色管理','/role','0','0000','0103','a');
INSERT INTO pub_menu VALUES ('000003','权限管理','/authority','0','0000','0104','a');
INSERT INTO pub_menu VALUES ('000004','资源管理','/resource','0','0000','0105','a');
INSERT INTO pub_menu VALUES ('000005','菜单管理','/menu','0','0000','0106','a');
INSERT INTO pub_menu VALUES ('000006','参数管理','/sysparam','0','0000','0107','a');
INSERT INTO pub_menu VALUES ('000009','数据字典','/datadictionary','0','0000','0108','a');
INSERT INTO pub_menu VALUES ('0001','测试用例','','1','00','02','');
INSERT INTO pub_menu VALUES ('000100','权限测试','/authTest','0','0001','0201','a');
INSERT INTO pub_menu VALUES ('000101','文件测试 ','/fileTest','0','0001','0202','a');
INSERT INTO pub_menu VALUES ('0002','三级菜单','','1','00','03','');
INSERT INTO pub_menu VALUES ('000200','二级菜单1','','1','0002','0301','');
INSERT INTO pub_menu VALUES ('00020000','测试用例1','/test1','0','000200','030101','a');
INSERT INTO pub_menu VALUES ('00020001','测试用例2','/test2','0','000200','030102','a');
INSERT INTO pub_menu VALUES ('000201','二级菜单2','','1','0002','0302','');
INSERT INTO pub_menu VALUES ('00020100','测试用例3','/test3','0','000201','030201','a');
INSERT INTO pub_menu VALUES ('0003','四级横向菜单','','1','00','04','i');
INSERT INTO pub_menu VALUES ('000300','三级菜单','','1','0003','0401','a');
INSERT INTO pub_menu VALUES ('00030000','二级菜单','','1','000300','040101','a');
INSERT INTO pub_menu VALUES ('0003000000','测试用例4','','1','00030000','04010101','a');
INSERT INTO pub_menu VALUES ('00030001','测试用例5','','1','000300','040102','a');
INSERT INTO pub_menu VALUES ('000301','测试用例6','','1','0003','0402','a');
INSERT INTO pub_menu VALUES ('0004','测试用例7','','1','00','','a');
commit;
-- ----------------------------
-- Table structure for pub_org
-- ----------------------------

CREATE TABLE pub_org (
  org_id varchar2(32) NOT NULL,
  org_code varchar2(32) DEFAULT NULL,
  org_name varchar2(64) DEFAULT NULL,
  enable char(1) DEFAULT NULL,
  org_address varchar2(100) DEFAULT NULL,
  org_desc varchar2(64) DEFAULT NULL,
  org_reserve1 varchar2(32) DEFAULT NULL,
  org_reserve2 varchar2(32) DEFAULT NULL,
  PRIMARY KEY (org_id)
) ;

-- ----------------------------
-- Records of pub_org
-- ----------------------------
INSERT INTO pub_org VALUES ('org0000','','景鸿科技','1','','组织机构根节点，请勿删除！','','');
INSERT INTO pub_org VALUES ('org_1475754962602','','123','1','','','','');
commit;

-- ----------------------------
-- Table structure for pub_org_desc
-- ----------------------------

CREATE TABLE pub_org_desc (
  id varchar2(32) NOT NULL,
  org_id varchar2(32) NOT NULL,
  org_level varchar2(2) NOT NULL,
  parent_id varchar2(32) NOT NULL,
  is_parent char(1) NOT NULL,
  open char(1) NOT NULL,
  PRIMARY KEY (id)
) ;

-- ----------------------------
-- Records of pub_org_desc
-- ----------------------------
INSERT INTO pub_org_desc VALUES ('9999','org0000','1','0','1','0');
INSERT INTO pub_org_desc VALUES ('99990000','org_1475754962602','2','9999','0','0');
commit;

-- ----------------------------
-- Table structure for pub_resources
-- ----------------------------

CREATE TABLE pub_resources (
  resource_id varchar2(32) NOT NULL,
  resource_name varchar2(64) NOT NULL,
  resource_type varchar2(32) NOT NULL,
  priority number(11) NOT NULL,
  resource_string varchar2(128) NOT NULL,
  resource_desc varchar2(128) DEFAULT NULL,
  enable char(1) NOT NULL,
  is_sys char(1) NOT NULL,
  PRIMARY KEY (resource_id)
) ;

-- ----------------------------
-- Records of pub_resources
-- ----------------------------
INSERT INTO pub_resources VALUES ('res1377768638475','[机构管理]_机构增加','b',0,'/org/addPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377868722342','[机构管理]_机构修改','b',0,'/org/updatePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377868741129','[机构管理]_机构删除','b',0,'/org/delOrgs','','1','1');
INSERT INTO pub_resources VALUES ('res1377877933606','[机构管理]_机构树查看','b',0,'/org/treePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893137228','[用户管理]_用户增加','b',0,'/user/addPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893155400','[用户管理]_用户修改','b',0,'/user/updatePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893170111','[用户管理]_用户删除','b',0,'/user/delUsers','','1','1');
INSERT INTO pub_resources VALUES ('res1377893215540','[用户管理]_用户角色','b',0,'/user/updateRolePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893266799','[角色管理]_角色增加','b',0,'/role/addPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893281414','[角色管理]_角色修改','b',0,'/role/updatePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893296002','[角色管理]_角色删除','b',0,'/role/delRoles','','1','1');
INSERT INTO pub_resources VALUES ('res1377893316245','[角色管理]_角色权限','b',0,'/role/updateAuthPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893364765','[权限管理]_权限增加','b',0,'/authority/addPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893378977','[权限管理]_权限修改','b',0,'/authority/updatePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893396054','[权限管理]_权限删除','b',0,'/authority/delAuthorities','','1','1');
INSERT INTO pub_resources VALUES ('res1377893444246','[权限管理]_权限资源','b',0,'/authority/updateResPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893503853','[资源管理]_资源增加','b',0,'/resource/addPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893518122','[资源管理]_资源修改','b',0,'/resource/updatePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893532098','[资源管理]_资源删除','b',0,'/resource/delResources','','1','1');
INSERT INTO pub_resources VALUES ('res1377893560233','[资源管理]_资源注册','b',0,'/resource/regResPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893577864','[资源管理]_资源查看','b',0,'/resource/resTreePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1377893867720','[菜单管理]_菜单添加','b',0,'/menu/addMenu','','1','1');
INSERT INTO pub_resources VALUES ('res1377893881340','[菜单管理]_菜单修改','b',0,'/menu/updateMenu','','1','1');
INSERT INTO pub_resources VALUES ('res1377893896653','[菜单管理]_菜单删除','b',0,'/menu/delMenu','','1','1');
INSERT INTO pub_resources VALUES ('res1377937279687','[系统默认]_系统登录','b',0,'/welcome','','1','0');
INSERT INTO pub_resources VALUES ('res1377947959118','[机构管理]_机构管理','b',0,'/org','','1','1');
INSERT INTO pub_resources VALUES ('res1377947996512','[机构管理]_机构查询','b',0,'/org/queryList','','1','1');
INSERT INTO pub_resources VALUES ('res1377948474223','[用户管理]_用户管理','b',0,'/user','','1','1');
INSERT INTO pub_resources VALUES ('res1377948503153','[用户管理]_用户查询','b',0,'/user/queryList','','1','1');
INSERT INTO pub_resources VALUES ('res1377949463024','[角色管理]_角色管理','b',0,'/role','','1','1');
INSERT INTO pub_resources VALUES ('res1377949502269','[角色管理]_角色查询','b',0,'/role/queryList','','1','1');
INSERT INTO pub_resources VALUES ('res1377953461190','[权限管理]_权限管理','b',0,'/authority','','1','1');
INSERT INTO pub_resources VALUES ('res1377953535482','[权限管理]_权限查询','b',0,'/authority/queryList','','1','1');
INSERT INTO pub_resources VALUES ('res1377953618390','[资源管理]_资源管理','b',0,'/resource','','1','1');
INSERT INTO pub_resources VALUES ('res1377953645526','[资源管理]_资源查询','b',0,'/resource/queryList','','1','1');
INSERT INTO pub_resources VALUES ('res1377953767306','[菜单管理]_菜单管理','b',0,'/menu','','1','1');
INSERT INTO pub_resources VALUES ('res1385021651938','[参数管理]_参数管理','b',0,'/sysparam','','1','1');
INSERT INTO pub_resources VALUES ('res1385021674643','[参数管理]_参数查询','b',0,'/sysparam/queryList','','1','1');
INSERT INTO pub_resources VALUES ('res1385021795181','[参数管理]_参数增加','b',0,'/sysparam/addPopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1385021889459','[参数管理]_参数修改','b',0,'/sysparam/updatePopWin','','1','1');
INSERT INTO pub_resources VALUES ('res1385022438821','[参数管理]_参数删除','b',0,'/sysparam/delSysParams','','1','1');
INSERT INTO pub_resources VALUES ('res1400748438871','[系统默认]_密码修改','b',0,'/user/updateLoginPwd','','1','0');
INSERT INTO pub_resources VALUES ('res1400830717774','[系统默认]_个人信息','b',0,'/user/loginMsg4Update','','1','0');
INSERT INTO pub_resources VALUES ('res1472440846167','权限测试','b',0,'/authTest','','1','1');
INSERT INTO pub_resources VALUES ('res1472441028703','文件测试','b',0,'/fileTest','','1','1');
INSERT INTO pub_resources VALUES ('res1472450909584','测试用例1','b',0,'/test1','','1','1');
INSERT INTO pub_resources VALUES ('res1472450919144','测试用例2','b',0,'/test2','','1','1');
INSERT INTO pub_resources VALUES ('res1472450926208','测试用例3','b',0,'/test3','','1','1');
INSERT INTO pub_resources VALUES ('res_1475891864334','[数据字典]_数据字典','b',0,'/datadictionary','','1','1');
commit;

-- ----------------------------
-- Table structure for pub_resources_menus
-- ----------------------------

CREATE TABLE pub_resources_menus (
  id varchar2(32) NOT NULL,
  resource_id varchar2(32) DEFAULT NULL,
  menu_id varchar2(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

-- ----------------------------
-- Records of pub_resources_menus
-- ----------------------------
INSERT INTO pub_resources_menus VALUES ('05297cbb771541b99e15a374cdcf43fe','res1385021651938','000006');
INSERT INTO pub_resources_menus VALUES ('1017f91ef78c48e8b86e64dc60f1b076','res1377893518122','000004');
INSERT INTO pub_resources_menus VALUES ('10a643b1870c4dda88709aab3595cee4','res1377953461190','000003');
INSERT INTO pub_resources_menus VALUES ('133d5e23bea444bebfd13bc7fddab2b0','res1377953618390','000004');
INSERT INTO pub_resources_menus VALUES ('1a139cd715b94c348f73689ecbed73a6','res1377893378977','000003');
INSERT INTO pub_resources_menus VALUES ('2478e29d8e034100bfbe8e96fcdbc530','res1472450919144','00020001');
INSERT INTO pub_resources_menus VALUES ('29fbacec85cc4b4a974b7870b5fd23cc','res1377893316245','000002');
INSERT INTO pub_resources_menus VALUES ('2a401dd8de774a12a5b13967b831db01','res1377893503853','000004');
INSERT INTO pub_resources_menus VALUES ('32328e52f69d422fb07b02b7c93ff220','res1377893444246','000003');
INSERT INTO pub_resources_menus VALUES ('341a08ef4a0f4beda523701a134141a2','res1377953767306','000005');
INSERT INTO pub_resources_menus VALUES ('381a48058f2b436fafc197fd65b09b2f','res1377893881340','000005');
INSERT INTO pub_resources_menus VALUES ('38bc66a323884a1086ffaf1e13c301c4','res1377893215540','000001');
INSERT INTO pub_resources_menus VALUES ('391a1094ffb04bc59321fe3e59013dc9','res1377949502269','000002');
INSERT INTO pub_resources_menus VALUES ('391f0f29494344dcb0644cbaef4cbc89','res1385021889459','000006');
INSERT INTO pub_resources_menus VALUES ('3b690c95c28b4c29bb5688d159b545b6','res1385022438821','000006');
INSERT INTO pub_resources_menus VALUES ('40aeae4308e14286aee18d10924f3c7a','res1377893155400','000001');
INSERT INTO pub_resources_menus VALUES ('40b3ff3bba9d4576a3f6b99713eb388c','res1377947996512','000000');
INSERT INTO pub_resources_menus VALUES ('46ddcd57c3db4046ba0af43325734c7a','res1377893170111','000001');
INSERT INTO pub_resources_menus VALUES ('4c5b3693a504407b963025a21aed1b56','res1377893577864','000004');
INSERT INTO pub_resources_menus VALUES ('4f4ae37480e240689a452d1dd6bbb3fb','res1377953645526','000004');
INSERT INTO pub_resources_menus VALUES ('5394ede9c5974d9381fdf639bf74069a','res1377893364765','000003');
INSERT INTO pub_resources_menus VALUES ('5695c7ae42ca4e19a18b69de733beff6','res1385021674643','000006');
INSERT INTO pub_resources_menus VALUES ('5b8ed07a391c4775926a9415c9c3c4ff','res1377893867720','000005');
INSERT INTO pub_resources_menus VALUES ('5d2fedffe34a452cbd87af4c3751da22','res_1475891864334','000009');
INSERT INTO pub_resources_menus VALUES ('6d4e0704900a4ed88b989672c95c6691','res1472441028703','000101');
INSERT INTO pub_resources_menus VALUES ('72a8e36652f34864adbe6520c0b9793a','res1377893396054','000003');
INSERT INTO pub_resources_menus VALUES ('80504f29de1e42388428011283b647eb','res1385021795181','000006');
INSERT INTO pub_resources_menus VALUES ('8181eeb68d64474b94468bfb0e360597','res1377893296002','000002');
INSERT INTO pub_resources_menus VALUES ('8dab73c0f62b44d7b85f5ff99ecddd7f','res1472440846167','000100');
INSERT INTO pub_resources_menus VALUES ('94bd90b243084d6db72b756d787ecc35','res1377893281414','000002');
INSERT INTO pub_resources_menus VALUES ('9684eacca6954ad39873a744f65f1c6a','res1377768638475','000000');
INSERT INTO pub_resources_menus VALUES ('974c33f4b1c04155b25fc70e1afbd8f2','res1377877933606','000000');
INSERT INTO pub_resources_menus VALUES ('a297fe37331e4a48a193963148a90ac6','res1377893266799','000002');
INSERT INTO pub_resources_menus VALUES ('b774ddc7fabc4592a721128d10f168a7','res1377893560233','000004');
INSERT INTO pub_resources_menus VALUES ('b9ad0698478d45c4bb545fff9b1a501c','res1472450926208','00020100');
INSERT INTO pub_resources_menus VALUES ('c8fbec680bb54c3cb56b1b1fe5221fd8','res1377893137228','000001');
INSERT INTO pub_resources_menus VALUES ('cd3f04e07c514c9eb170b3a684e039d7','res1377868741129','000000');
INSERT INTO pub_resources_menus VALUES ('cdc95cffb3b14d9796aba54019b47dab','res1377947959118','000000');
INSERT INTO pub_resources_menus VALUES ('d2bbb8c688fa441b8d7b9f77a68ceeda','res1377893896653','000005');
INSERT INTO pub_resources_menus VALUES ('df2e8ff5c114428bacbd7ec73fec2dea','res1377868722342','000000');
INSERT INTO pub_resources_menus VALUES ('e5bef012fd6149d5b20eafb98230baf2','res1377948503153','000001');
INSERT INTO pub_resources_menus VALUES ('ea506635167b4726a395e40656370197','res1472450909584','00020000');
INSERT INTO pub_resources_menus VALUES ('f33237026a5848efa13dae9ca85836a6','res1377893532098','000004');
INSERT INTO pub_resources_menus VALUES ('f6895e9f4157470f838e49b34da22966','res1377948474223','000001');
INSERT INTO pub_resources_menus VALUES ('fbfc555c23974241b103cd3013098a34','res1377953535482','000003');
INSERT INTO pub_resources_menus VALUES ('fd44ac71777047558a10cf55ca5c2cba','res1377949463024','000002');
commit;
-- ----------------------------
-- Table structure for pub_roles
-- ----------------------------

CREATE TABLE pub_roles (
  role_id varchar2(32) NOT NULL,
  role_name varchar2(64) NOT NULL,
  role_desc varchar2(128) DEFAULT NULL,
  role_level varchar2(4) DEFAULT NULL,
  enable char(1) NOT NULL,
  is_sys char(1) NOT NULL,
  PRIMARY KEY (role_id)
) ;

-- ----------------------------
-- Records of pub_roles
-- ----------------------------
INSERT INTO pub_roles VALUES ('role1377351043380','超级管理员','','1','1','1');
INSERT INTO pub_roles VALUES ('role1400838519752','一级管理员','','2','1','1');
INSERT INTO pub_roles VALUES ('role1400838540878','二级管理员','','3','1','1');
commit;
-- ----------------------------
-- Table structure for pub_roles_authorities
-- ----------------------------

CREATE TABLE pub_roles_authorities (
  id varchar2(32) NOT NULL,
  role_id varchar2(32) NOT NULL,
  authority_id varchar2(32) NOT NULL,
  PRIMARY KEY (id)
) ;

-- ----------------------------
-- Records of pub_roles_authorities
-- ----------------------------
INSERT INTO pub_roles_authorities VALUES ('134892464b2f407c935a555ce2b60d7c','role1400838519752','AUTH_1377925320407');
INSERT INTO pub_roles_authorities VALUES ('57df64599dee456187dcb6a8cb46bea0','role1400838540878','AUTH_1475889178590');
INSERT INTO pub_roles_authorities VALUES ('613e28d3264f4ba88bd13a01d0f5f8bd','role1377351043380','AUTH_1472450956478');
INSERT INTO pub_roles_authorities VALUES ('6a7d3403d4324545a455d9d420a0361c','role1377351043380','AUTH_1472442396521');
INSERT INTO pub_roles_authorities VALUES ('776d821db0634e1d81524724e6fb6902','role1377351043380','AUTH_1472450971285');
INSERT INTO pub_roles_authorities VALUES ('82da7170434743d3a78c943cd8347bc8','role1377351043380','AUTH_1472450976416');
INSERT INTO pub_roles_authorities VALUES ('affb6a87433d4741aa955bc557276ea0','role1377351043380','AUTH_1475889178590');
INSERT INTO pub_roles_authorities VALUES ('b368bc2562ff499d9c10dde3f61eff18','role1400838540878','AUTH_1377925254409');
INSERT INTO pub_roles_authorities VALUES ('be15facf89354e3183772514ccc103f5','role1377351043380','AUTH_1472442424121');
INSERT INTO pub_roles_authorities VALUES ('ee03875c86da4f47a7ddfcb26af4ce78','role1400838519752','AUTH_1475889178590');
INSERT INTO pub_roles_authorities VALUES ('f268000814904be69890960cc33c1992','role1400838540878','AUTH_1377925320407');
INSERT INTO pub_roles_authorities VALUES ('fcc5ce0013b24ba29847cb4f1c52af7f','role1400838519752','AUTH_1377925254409');
INSERT INTO pub_roles_authorities VALUES ('fe9b88dea4a74c2a85be376b046c4d9f','role1377351043380','AUTH_1475894194473');
commit;

-- ----------------------------
-- Table structure for pub_sys_param
-- ----------------------------

CREATE TABLE pub_sys_param (
  param_id varchar2(32) NOT NULL,
  param_code varchar2(32) NOT NULL,
  param_name varchar2(64) NOT NULL,
  param_value varchar2(64) DEFAULT NULL,
  param_status char(1) DEFAULT NULL,
  PRIMARY KEY (param_id)
) ;

-- ----------------------------
-- Records of pub_sys_param
-- ----------------------------
INSERT INTO pub_sys_param VALUES ('186d6188e1214489aad9e02f737beafb', 'API-KEY', '短信密钥', 'b832f01bb37905df13affa7a93350b31', '1');
INSERT INTO pub_sys_param VALUES ('fcfaefbef57a417fa4623959b47f50b5', 'SOCK-KEY', '通信加密密钥', '1234567890abcdef0abcdef123456789', '1');
INSERT into PUB_SYS_PARAM VALUES ('E28501EC2FD84AD39881A0D420E61B3D', 'DEFAULT_FILE_FOLDER', '默认文件存储文件夹', 'd:/testfileupload', '1');
INSERT into PUB_SYS_PARAM VALUES ('3EF977F5F2681493E0538701A8C0FA2B', 'EXCEL_EXPORT_TMP_FOLDER', 'excel导出临时存放目录', 'd:/excelexporttmp', '1');
commit;
-- ----------------------------
-- Table structure for pub_users
-- ----------------------------

CREATE TABLE pub_users (
  user_id varchar2(32) NOT NULL,
  user_account varchar2(32) NOT NULL,
  user_name varchar2(64) DEFAULT NULL,
  user_password varchar2(32) NOT NULL,
  user_gender char(1) DEFAULT NULL,
  user_birthday varchar2(12) DEFAULT NULL,
  user_org varchar2(32) DEFAULT NULL,
  user_duty varchar2(32) DEFAULT NULL,
  user_telephone varchar2(20) DEFAULT NULL,
  mail varchar2(64) DEFAULT NULL,
  qq_weixin varchar2(32) DEFAULT NULL,
  user_desc varchar2(128) DEFAULT NULL,
  enable char(1) NOT NULL,
  is_sys char(1) NOT NULL,
  err_times varchar2(4) DEFAULT NULL,
  PRIMARY KEY (user_id)
) ;

-- ----------------------------
-- Records of pub_users
-- ----------------------------
INSERT INTO pub_users VALUES ('u0001','admin','管理员','32057b94d50d40cac3bb0177b5923c50','1','2013-11-18','org0000',NULL,'10086','','','','1','1','0');
INSERT INTO pub_users VALUES ('user1401092655973','aa1','aa1','33bcba31e56a2d4f6157cda9e2ada74e','1',NULL,'org1400834058749','','','','','','1','1','0');
INSERT INTO pub_users VALUES ('user1401092670707','aa2','aa2','068a74bdea835beebffe160d72c800e2','1',NULL,'org1400834068242','','','','','','1','1','0');
INSERT INTO pub_users VALUES ('user_1475754971209','123','123','2d1f5c7b21830509b2488f75d0e5da22','1','','org_1475754962602','','','','','','1','1','0');
commit;
-- ----------------------------
-- Table structure for pub_users_roles
-- ----------------------------

CREATE TABLE pub_users_roles (
  id varchar2(32) NOT NULL,
  user_id varchar2(32) NOT NULL,
  role_id varchar2(32) NOT NULL,
  PRIMARY KEY (id)
) ;

-- ----------------------------
-- Records of pub_users_roles
-- ----------------------------
INSERT INTO pub_users_roles VALUES ('23e43087419847dfac84595c2aa72ad7','u0001','role1377351043380');
INSERT INTO pub_users_roles VALUES ('9b23a93d616a4d70be0c5de3cb5e7822','user_1475754971209','role1400838519752');
commit;

-- ----------------------------
-- Table structure for PUB_FILE_UPLOAD
-- ----------------------------
CREATE TABLE PUB_FILE_UPLOAD
(
  FILE_ID      VARCHAR2(32),
  FILE_NAME    VARCHAR2(256)               NOT NULL,
  FILE_TYPE    VARCHAR2(128),
  FILE_PATH    VARCHAR2(512)               NOT NULL,
  UPLOAD_USER  VARCHAR2(64),
  UPLOAD_TIME  VARCHAR2(20),
  FILE_REG     VARCHAR2(256),
PRIMARY KEY (FILE_ID)
);

COMMENT ON TABLE PUB_FILE_UPLOAD IS '文件上传表';

COMMENT ON COLUMN PUB_FILE_UPLOAD.FILE_ID IS '文件ID';

COMMENT ON COLUMN PUB_FILE_UPLOAD.FILE_NAME IS '文件名称';

COMMENT ON COLUMN PUB_FILE_UPLOAD.FILE_TYPE IS '文件类型';

COMMENT ON COLUMN PUB_FILE_UPLOAD.FILE_PATH IS '文件全路径';

COMMENT ON COLUMN PUB_FILE_UPLOAD.UPLOAD_USER IS '上传人';

COMMENT ON COLUMN PUB_FILE_UPLOAD.UPLOAD_TIME IS '上传时间';

COMMENT ON COLUMN PUB_FILE_UPLOAD.FILE_REG IS '备注';
