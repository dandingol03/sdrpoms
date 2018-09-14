git config --global user.name "jiawenlong"
git config --global user.email "1183011789@qq.com"
Create git repository:

mkdir 111111111
cd 111111111
git init
touch README.md
git add README.md
git commit -m "first commit"
git remote add origin https://gitee.com/wenlongjia/111111111.git
git push -u origin master
Existing project?

cd existing_git_repo
git remote add origin https://gitee.com/wenlongjia/111111111.git
git push -u origin master