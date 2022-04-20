class Hero {
  constructor() {
    this.hero = document.createElement("div");
    bg.appendChild(this.hero);
  }
  createHero(x,y) {
    this.hero.style.left = `${x}px`;
    this.hero.style.top = `${y}px`;
    this.hero.className = "heroFront";
  }
  heroLeft() {
    this.hero.className = "heroLeft";
  }
  heroRight() {
    this.hero.className = "heroRight";
  }
  heroBack() {
    this.hero.className = "heroBack";
  }
  heroFront(){
    this.hero.className= "heroFront";
  }
}
