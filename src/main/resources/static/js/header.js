async function coinUpdate(){
    let id = $("#id").val();
    let coinUpdateDto = {
        coin:$("#user-coin").val()
    }
    console.log(coinUpdate);
    let response = await fetch(`/user/${id}`,{
        method:"PUT",
        body:JSON.stringify(coinUpdateDto),
        headers:{
           "Content-Type":"application/json; charset=utf-8"
        }
    });
    
    let responseParse = await response.json();
    
}
coinUpdate();