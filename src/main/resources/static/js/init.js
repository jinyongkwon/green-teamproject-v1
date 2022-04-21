const bgfield = document.querySelector("#bgfield");

let agressPoint =0;

let heroX = 50;
const heroWidth = 40;
const heroHeight = 55;
let heroY = 50;

let enemyGauge =0;

let enemyX = 100;
const enemyWidth = 45;
const enemyHeight = 54;
let enemyCount = 10;
let enemyList = [];

let right = false;
let left = false;
let up = false;
let down = false;
const movingGap = 19;

//let encounterSound = new Audio(`wow.mp3`);

let HeroImg = new Hero();
let EnemyImg = new Enemy();


/*
$("#save-battle").click(()=>{
saveBattle();
});

async function saveBattle(){
  
  let writeDto = {enemy : enemyList};
    console.log(writeDto);
  let response = await fetch("/checkTest/save",{
    method : "POST",
    body : JSON.stringify(writeDto),
    headers : {
      "Content-Type": "application/json; charset=utf-8"
      }
  });
  let responseParse= await response.json();

  if( responseParse.code==1){
    alert("성공");
    location.href="/checkTest";
  } else {
    alert("fail");
      }
  }
*/
/*
$("#save-battle").click(()=>{
$.ajax({
  url         :   "/checkTest/save",
  dataType    :   "json",
  contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
  type        :   "POST",
  data        :   enemyList,
  traditional : true,
  success     :   function(retVal){

      if(retVal.code == "OK") {
          alert(retVal.message);
      } else {
          alert(retVal.message);
      }
       
  },
  error       :   function(request, status, error){
      console.log("AJAX_ERROR");

  }
}

)}
})
*/
function moveRight() {
  if (right) {
    if (heroX < bgfield.parentElement.clientWidth*0.975) {
      console.log("clientwidth"+bgfield.clientWidth);
      heroX += movingGap;
      enemyGauge+= movingGap;
      console.log(enemyGauge);      
    }
    HeroImg.createHero(heroX, heroY);
    HeroImg.heroRight();
  }
}

function moveLeft() {
  if (left) {
    if (heroX > 0) {
      heroX -= movingGap;
      enemyGauge+= movingGap;
      console.log(enemyGauge);
    }
    HeroImg.createHero(heroX, heroY);
    HeroImg.heroLeft();
  }
}

function moveDown() {
  if (down) {
    if (heroY < bgfield.clientHeight*0.895) {
      
      heroY += movingGap;
      enemyGauge+= movingGap;
      console.log(enemyGauge);
    }
    HeroImg.createHero(heroX, heroY);
    HeroImg.heroFront();
  }
}
function moveUp() {
  if (up) {
    if (heroY > 0) {
      heroY -= movingGap;
      enemyGauge+= movingGap;
      console.log(enemyGauge);
    }
    HeroImg.createHero(heroX, heroY);
    HeroImg.heroBack();
  }
}


function handleKeydown(e) {
  const key = e.keyCode;
  if (key === 39) {
    right = true;
  } else if (key === 37) {
    left = true;    
  }
  if(key === 38) {
    up = true;
  } else if (key=== 40) {
    down = true;
    }
  }  


function handleKeyup(e) {
  const key = e.keyCode;
  if (key === 39) {
    right = false;
  } else if (key === 37) {
    left = false;
  }
  if(key === 38) {
    up = false;
  } else if (key=== 40) {
    down = false;
    }
  
}

function randomiseX() {
  return Math.floor(Math.random() * 1255);
}
function randomiseY() {
  return Math.floor(Math.random() * 612);
}

function makeEnemyList() {
  array.forEach(enemy => {
    
  });
  /*for (let i = 0; i < enemyCount; i++) {
    enemy = {};
    enemy["EnemyImg"] = new Enemy();
    enemy["status"] = true;
    enemy["x"] = randomiseX();
    enemy["y"] = randomiseY();
    
    enemyList.push(enemy);
  }*/
}

function makeEnemy() {
  //여기서 레디스의 에너미를 먼저 뿌린다

  if (enemyList.length< 10){
    if (enemyGauge >230){
    let checkVal = Math.round(Math.random()*100);
    //console.log(" 이거는"+ checkVal);
    if(checkVal>89){
      
      enemy = {};
    enemy["EnemyImg"] = new Enemy();
    enemy["status"] = true;
    enemy["x"] = randomiseX();
    enemy["y"] = randomiseY();
    let a= enemy["x"];
    let b = enemy["y"];
    
    enemy.EnemyImg.createEnemy(a,b);
    
    enemyList.push(enemy);
      enemyGauge =0;
          } 
  }
  
    }
  }

function encounter() {
  let pad = 10;
  
  enemyList.forEach(enemy => {
    if (enemy.status === true) {
      if (
        heroX + pad <= enemy.x + enemyWidth &&
        heroX + heroWidth >= enemy.x + pad
      ) {
        if (heroY + pad < enemy.y + enemyHeight &&
              heroY + heroHeight >= enemy.y +pad
          ) {
          enemy.status = false;
          enemy.EnemyImg.killEnemy();
          setTimeout(detectEncounter, 2000);
 
          //여기서 정보를 레디스로 넘긴다.
          
          
              window.location.replace("/s/battle");
      
          //audioplay(encounterSound);
          
          
          
        }
      }
    }
  });
}


function detectEncounter() {
  enemyList.forEach(enemy => {
    if (enemy.status === false) {
      enemy.EnemyImg.removeEnemy();
      
    }
  });
}

function audioplay(){
   // let mp3Source = '<source src="wow.mp3" type="audio/mpeg">';
}


function init() {
  HeroImg.createHero(heroX, heroY);
  moveLeft();
  moveRight();
  moveUp();
  moveDown();
  makeEnemy();
  encounter();
  
  }

document.addEventListener("keydown", handleKeydown);
document.addEventListener("keyup", handleKeyup);
//document.addEventListener("ValueChange")
setInterval(init, 100);
//makeEnemyList();

