const userName = document.getElementById("userName");
const userNameResult = document.getElementById("userNameResult")

userName.addEventListener('change', ()=>{
    
    console.log(userName.value);

    fetch("./check?userName="+userName.value)
    .then(res => res.text())
    .then(res =>{
        if(res.trim()=='0'){
            //가입 X
            userNameResult.innerHTML='중복된 ID 입니다'
        }else {
            //가입 o
            userNameResult.innerHTML='사용 가능한 ID 입니다'
        }
    })

})