class Enemy {
  constructor() {
    this.enemy = document.createElement("div");
  }

  createEnemy(x, y) {
    bg.appendChild(this.enemy);
    this.enemy.className = "enemy";
    this.enemy.style.left = `${x}px`;
    this.enemy.style.top = `${y}px`;
  }

  killEnemy() {
    this.enemy.className = "enemyDie";
  }
  removeEnemy() {
    this.enemy.className = "enemyGone";
  }
}
