$("#btn-ready").click(()=>{
 weaponUpdate();
})

async function weaponUpdate(){
    let id = $("#id").val();

    let weaponUpdateDto ={
        html:$("#html").val(),
        java:$("#java").val(),
        jsp:$("#jsp").val(),
        spring:$("#spring").val()
    }
    let response = await fetch(`/s/user/${id}` , {
        method:"PUT",
        body: JSON.stringify(weaponUpdateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    console.log(response);

   let responseParse = await response.json();
   console.log(responseParse);
   if (responseParse.code == 1) {
    location.href = "/s/ready";
} else {
    alert("실패")
}
}


