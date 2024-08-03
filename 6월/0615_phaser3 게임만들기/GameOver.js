
class GameOver extends Phaser.Scene {


    constructor() {
        super({ key: "GameOver" });
    }


    preload() {
        this.load.image('gameoverImg', '/images/gameover2.png');
        this.load.image('restartBtn', '/images/restart.png');
    }

    create() {
        let gameoverImg = this.add.image(this.cameras.main.width / 2, this.cameras.main.height / 2 - 70, 'gameoverImg');
        gameoverImg.setOrigin(0.5);
        gameoverImg.setScale(1.2);


        // reStart 버튼 추가
        let restartBtn = this.add.sprite(this.cameras.main.width / 2, this.cameras.main.height / 2 + 160, 'restartBtn').setInteractive();
        restartBtn.setScale(0.7);

        restartBtn.on('pointerdown', () => {
            this.scene.start('Exam03');
        });
        // 버튼에 마우스 올릴 시 크기조절 및 효과
        restartBtn.on("pointerover", () => {
            restartBtn.setScale(0.8);
            this.game.canvas.style.cursor = "pointer";
        });
        restartBtn.on("pointerout", () => {
            restartBtn.setScale(0.7);
            this.game.canvas.style.cursor = "default";
        });

    }

    update() {

    }

}







