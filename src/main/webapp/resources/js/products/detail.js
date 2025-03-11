//수정 버튼을 클릭 했을 때 콘솔에 출력
//form method의 값을 콘솔에 출력력
//삭제 버튼을 클릭 했을 때 콘솔에 출력
//form action의 주소값을 콘솔에 출력
const up = document.getElementById("up");
const del = document.getElementById("del");
const frm = document.getElementById("frm");
const addCart= document.getElementById("addCart")
const addComments = document.getElementById("addComments");
const commentsContents = document.getElementById("commentsContents");
const commentsListResult = document.getElementById("commentsListResult");
const pages = document.getElementsByClassName("pages");


addCart.addEventListener("click", ()=>{
    let num = addCart.getAttribute("data-product-num")
    let s = `hello ${num}`

    fetch(`../users/addCart?productNum=${num}`)
    .then(res => res.text())
    .then(res => {
        if(res.trim()=="1"){   //parseInt("1"), "1"+1
            let r = confirm("장바구니로 이동??")
            if(r){
                location.href='../users/carts';
            }
        }else {
            alert('장바구니 등록 실패')
        }
    }).catch(r=>{
        alert('장바구니 등록 실패')
    })
})

try {
    up.addEventListener("click", function(){
        console.log(frm.method) //GET
        console.log(frm.getAttribute("method"))//null
        console.log("수정")
        frm.action="./update";
        frm.submit();
    })
} catch (error) {
    
}

del.addEventListener("click", function(){
    console.log(frm.action); //url
    console.log(frm.getAttribute("action"));//uri
    console.log("삭제")

    let check = confirm("정말 삭제???");
    if(check){
        frm.action="./delete";
        frm.method="POST";
        frm.submit();
    }
})

//------------- 등록 버튼 ----------------------
addComments.addEventListener("click", async ()=>{
    console.log(commentsContents.value);
    console.log(addCart.getAttribute("data-product-num"));
    
    await add()
    await getList(1)

   

})

function makeForm(pn, contents){
    let f = new FormData();
    f.append("productNum", pn);
    f.append("boardContents", contents)

    return f;
}

function makeParam(pn, contents){

    let p = new URLSearchParams();
    p.append("productNum", pn);
    p.append("boardContents", contents)

    return p;
}

getList(1)

async function getList(page){
    let pn = addCart.getAttribute("data-product-num");
    fetch(`listComments?productNum=${pn}&page=${page}`)
    .then(r => r.text())
    .then(r => {
        commentsListResult.innerHTML=r;
    })
    .catch(e=> console.log(e))
    
}

async function add(){
    let pn = addCart.getAttribute("data-product-num");

    //let p = makeParam(pn, commentsContents.value);
    let p = makeForm(pn, commentsContents.value)

   

    fetch('./addComments', {
        method:'POST',
        // headers: {
        //     "Content-type":"application/x-www-form-urlencoded; charset=UTF-8"
        // },
        //body: `productNum=${pn}&boardContents=${commentsContents.value}`
        body:p
    })
    .then(r=>r.text())
    .then(r=>{
        //getList()
        if(r.trim()*1>0){

        }else {

        }

        commentsContents.value="";

    })
    .catch(e =>{
        alert('에러 발생')
    })
}

commentsListResult.addEventListener('click', (e)=>{
    if(e.target.classList.contains('pages')){
        let p = e.target.getAttribute("data-page-num");
        getList(p)
    }
})