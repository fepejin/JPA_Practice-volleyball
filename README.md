# projecthta

필요할때 보려고 만드는 기초 명령어

## 🌿github에 repo 생성
## 🌿내 컴퓨터에 연동시켜줄 디렉토리 생성


## 🌿실행 후 해야 할 것
### 🌱 user.name / user.email 설정
```git
git config --global user.name "닉"
git config --global user.email "메일"

```

💫오타났을때 삭제

1. 전체를 삭제하고 싶을때(예시 name) 
```git
git config --global --unset-all user.name
```
2. 특정 이름만 삭제하고 싶을때 (예시 name)
```git
git config --global --unset user.name "닉"
```


### 🌱최초로 프로젝트를 올릴때 초기화
```git
git init
Initialized empty Git repository in 디렉토리//가 나오면 성공
```
`이후에 branch명 생성`

💫branch명 변경(나는 main으로 변경함)
```git
git branch -M 변경branch명
```

### 🌱 github와 연동
`1. github에서 생성한 repo로 들어가서 code 클릭 - 주소 복사`
![](https://velog.velcdn.com/images/noljis95/post/5d8c25ab-d12a-40c7-9e2c-8072becb6ac3/image.png)
`2. git bash 명령어 뒤에 붙여넣기`
```git
git remote add origin 주소복붙
```
복붙 단축키는 shift + ins
`3. git remote -v로 연결 잘됐는지 확인`


### 🌱디렉토리에서 github로 연동시켜줄 파일들 추가
1. 전체 추가
```git
git add .
```
2. 하나 추가
```git 
git add 파일명(확장자까지)
```
3. git status로 중간중간 상태 확인

### 🌱추가한 파일들 commit하기("진짜_최종"같은 히스토리 만들어주기)
```git
git commit -m "커밋문구"
```


### 🌱github에 추가한 파일들 push

```git
git push origin main(branch명)
```
💫주의: 생성한 repo에 파일이 이미 있으면 pull먼저 하기
```git
git pull origin main
```
💫 push 강제로 하기
(웬만하면 안하는쪽으로.. Push가 안된다면 init이 제대로 됐는지 확인해보자)
```git
git push -u origin +main
```

### 🌱프로젝트를 수정한다면..
또 다시 add-commit-push를 해서 갱신 할 것
```git 
git add .
```

