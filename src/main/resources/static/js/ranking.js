async function rank(){
  // console.log(keyword);

    let response = await fetch("/s/api/user-all"); 
    let responseParse = await response.json();

    console.log(responseParse);

    // CSR
    if(responseParse.code == 1){
        $("#board-box").empty(); 
       for(boardTbl of responseParse.data){
        $("#board-box").append(trRender(boardTbl));
       }  
    }
 }
function trRender(user){
    return `<tr>
        <td>${user.ranking.rank}</td>
        <td>${user.nickname}</td>
        <td>${user.ranking.score}</td>
           </tr>`;
}
rank();

$("#btn-search").click(()=>{
    
   let keyword = $("#keyword").val();
   
   if(keyword == ""){
       rank();
   }
else{search(keyword);}
})
 async function search(keyword){
   // console.log(keyword);
     let response = await fetch(`/api/search?keyword=${keyword}`); 
     let responseParse = await response.json();
     console.log(responseParse);
     // CSR
     if(responseParse.code == 1){
        $("#board-box").empty(); 
        $("#board-box").append(trRender(responseParse.data));
       
    }
 }

search("");