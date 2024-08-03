class Exam03 extends Phaser.Scene {

    constructor() {
        super({ key: "Exam03" });
        this.cookies = [];
        this.eagles = [];
        this.frame = 0;
        this.timer = 0;
        this.score = 0;
        this.jumpCount = 0;
        this.tileSpeed = 3;
        this.isStart = false;

    }

    preload() {
        this.load.image('background', '/images/background4.jpg');
        this.load.spritesheet('bird', '/images/bird.png', { frameWidth: 135, frameHeight: 123 });
        this.load.image('cookie', '/images/cookie.png');
        this.load.spritesheet('eagle', '/images/eagle.png', { frameWidth: 360, frameHeight: 86.625 }); // 스프라이트 시트 로드
        this.load.image('startBtn', '/images/startbtn.png');
        this.load.image('startImg', '/images/start.png');
        this.load.image('score', '/images/score.png');
        this.load.image('timer', '/images/timer.png');
        this.load.font('font', '/font/font.ttf');
    }
    init() {
        this.cookies = [];
        this.eagles = [];
        this.frame = 0;
        this.timer = 0;
        this.score = 0;
        this.jumpCount = 0;
        this.tileSpeed = 2;
        this.isStart = false;
    }
    create() {
        this.cameras.main.setBackgroundColor("#ffffff");

        // 사용자 움직임
        this.anims.create({
            key: "run",
            frames: this.anims.generateFrameNumbers('bird', { start: 0, end: 13 }),
            frameRate: 10, // 숫자 높아질수록 빠르게 움직임
            repeat: -1 // 무한반복
        });
        this.anims.create({
            key: "jump",
            frames: this.anims.generateFrameNumbers('bird', { frames: [2, 3, 4, 5] }),
            frameRate: 5,
            repeat: -1
        });

        // 장애물 움직임 (독수리)
        this.anims.create({
            key: "come",
            frames: this.anims.generateFrameNumbers('eagle', { start: 0, end: 7 }),
            frameRate: 10, // 숫자 높아질수록 빠르게 움직임
            repeat: -1 // 무한반복
        });

        // 배경
        this.background = this.add.tileSprite(0, 0, this.cameras.main.width, this.cameras.main.height, 'background');
        this.background.setOrigin(0, 0);

        // 바닥선
        let floor = this.add.rectangle(0, 530, this.cameras.main.width, 5, "#000000", 0);
        floor.setOrigin(0, 0);

        // 왼쪽 바깥선
        let leftBoundary = this.add.rectangle(-30, 0, 5, this.cameras.main.height, "#000000");
        leftBoundary.setOrigin(0, 0);
        this.physics.add.existing(leftBoundary, true);
        this.physics.add.collider(this.cookies, leftBoundary, (cookie, boundary) => {
            cookie.destroy();
            this.cookies.splice(this.cookies.indexOf(cookie), 1);
        });
        this.physics.add.collider(this.eagles, leftBoundary, (eagle, boundary) => {
            eagle.destroy();
            this.eagles.splice(this.eagles.indexOf(eagle), 1);
        });

        // 사용자
        this.player = this.physics.add.sprite(90, 320, 'bird'); // ***y좌표 위치 
        this.player.setCollideWorldBounds(true);
        this.player.anims.play({ key: 'run' });
        this.player.setData("onFloor", true); // => request.setAttribute


        let scaleFactor = 80 / 135; // 원하는 사이즈 / 원본이미지 사이즈
        this.player.setScale(scaleFactor);
        this.player.setSize(20 / scaleFactor, 25 / scaleFactor);


        this.physics.add.existing(floor, true);
        this.physics.add.collider(this.player, floor, (player, floor) => {
            player.setData("onFloor", true);
        });

        // 쿠키 먹을 시 점수 +5
        this.physics.add.overlap(this.cookies, this.player, (cookie, player) => {
            this.score += 50;
            this.scoreText.setText(this.score);
            document.getElementById("score").innerHTML = this.score;
            cookie.destroy();
            this.cookies.splice(this.cookies.indexOf(cookie), 1);
        });

        // 독수리와 충돌 시 게임 오버 처리
        this.physics.add.collider(this.player, this.eagles, (player, eagle) => {
            this.scene.start('GameOver'); // GameOver 씬으로 전환
        });

        // 타이머
        // 타이머 이미지
        let timerImg = this.add.image(60, 50, 'timer');
        timerImg.setScale(0.13);
        // 타이머 텍스트
        this.timerText = this.add.text(120, 40, '0', {
            fontFamily: 'font',
            fontSize: '26px',
            fill: '#000000'
        });

        // 점수
        // 점수 이미지
        let scoreImg = this.add.image(820, 53, 'score');
        scoreImg.setScale(0.2);
        // 점수 텍스트
        this.scoreText = this.add.text(940, 40, '0', {
            fontFamily: 'font',
            fontSize: '26px',
            fill: '#000000'
        });

        this.cursor = this.input.keyboard.createCursorKeys();

        this.cameras.main.setBackgroundColor("#ffffff");
        // 투명한 회색 배경을 추가합니다.
        let startBackgroud = this.add.rectangle(0, 0, this.cameras.main.width, this.cameras.main.height, 0x000000, 0.5).setOrigin(0);

        let startImg = this.add.sprite(this.cameras.main.width / 2, this.cameras.main.height / 2 - 70, 'startImg').setInteractive();
        // startImg.setScale(0.9);


        // Start 버튼 추가
        let startButton = this.add.sprite(this.cameras.main.width / 2, this.cameras.main.height / 2 + 160, 'startBtn').setInteractive();
        startButton.setScale(0.7);

        startButton.on('pointerdown', () => {
            this.isStart = true;
            startImg.destroy(); // Start 이미지 제거
            startButton.destroy(); // Start 버튼 제거
            startBackgroud.destroy();
        });
        // 버튼에 마우스 올릴 시 크기조절 및 효과
        startButton.on("pointerover", () => {
            startButton.setScale(0.8);
            this.game.canvas.style.cursor = "pointer";
        });
        startButton.on("pointerout", () => {
            startButton.setScale(0.7);
            this.game.canvas.style.cursor = "default";
        });


    }

    update() {
        if (this.isStart) {
            this.player.setGravity(0, 700);

            // 테두리 충돌 감지
            // if (this.player.y <= 0 || this.player.y >= this.cameras.main.height || this.player.x <= 0 || this.player.x >= this.cameras.main.width) {
            //     this.scene.start('GameOver'); // 게임 오버 씬으로 전환
            // }

            // 타일 속도 조정
            if (this.timer > 20) {
                this.tileSpeed = 5; // 15초 후에 배경 속도 증가
            } else if (this.timer > 10) {
                this.tileSpeed = 4; // 10초 후에 배경 속도 증가
            }

            this.background.tilePositionX += this.tileSpeed;
            this.frame++;

            // JustDown : 키 입력을 한번만 감지 (누르고 있는 행동을 무시)
            if (Phaser.Input.Keyboard.JustDown(this.cursor.space)) {
                this.player.setVelocityY(-300); //중력 거스르는 작용
                this.player.play("jump");
                this.player.setData("onFloor", false);
                this.jumpCount++;
            }

            // 사용자 움직임 조작
            if (this.cursor.left.isDown) {
                this.player.setVelocityX(-200);
            } else if (this.cursor.right.isDown) {
                this.player.setVelocityX(200);
            } else {
                this.player.setVelocityX(0); // 손 떼는 순간 속도 0
            }

            // 플레이어가 바닥에 있고 뛰는 애니메이션이 아닌 경우
            if (this.player.getData("onFloor") && this.player.anims.currentAnim.key != "run") {
                this.player.play("run");
                this.jumpCount = 0;
            }

            // 타이머
            if (this.frame % 60 == 0) {
                this.timer++;
                this.timerText.setText(this.timer);
                document.getElementById("timer").innerHTML = this.timer;
            }

            // 쿠키 & 독수리 랜덤 생성
            if (this.frame % 120 == 0) {
                let rand = Math.floor(Math.random() * 5); // 0부터 5까지의 랜덤 숫자 생성

                if (this.timer < 15) {
                    if (rand >= 0 && rand <= 1) {
                        // 쿠키 생성
                        let cookie = this.physics.add.sprite(1010, Math.random() * (480 - 50 + 1) + 50, "cookie");
                        cookie.setVelocityX(Math.random() * (170 - 220 + 1) - 170);


                        let cookie_scaleFactor = 35 / 512;
                        cookie.setScale(cookie_scaleFactor);
                        cookie.setSize(15 / cookie_scaleFactor, 15 / cookie_scaleFactor);
                        this.cookies.push(cookie);
                    } else if (rand >= 2 && rand <= 3) {
                        // 독수리 생성
                        // let eagleY = Math.random() * (480 - 150) + 75;
                        let eagleY = Math.random() * (530 - 150 + 1) + 150;
                        let eagle = this.physics.add.sprite(1010, eagleY, 'eagle').setOrigin(0.5, 1);

                        let eagle_scaleFactorX = 230 / 360;
                        let eagle_scaleFactorY = 70 / 86.625;
                        eagle.setScale(eagle_scaleFactorX, eagle_scaleFactorY);
                        eagle.setSize(40 / eagle_scaleFactorX, 20 / eagle_scaleFactorY);

                        eagle.setVelocityX(-220);
                        eagle.anims.play('come');
                        this.eagles.push(eagle);
                    }

                } else if (this.timer > 16) {

                    if (rand >= 0 && rand <= 1) {
                        // 쿠키 생성
                        let cookie = this.physics.add.sprite(1010, Math.random() * (480 - 50 + 1) + 50, "cookie");
                        cookie.setVelocityX(Math.random() * (190 - 250 + 1) - 190 * (this.tileSpeed / 2));

                        let cookie_scaleFactor = 35 / 512;
                        cookie.setScale(cookie_scaleFactor);
                        cookie.setSize(15 / cookie_scaleFactor, 15 / cookie_scaleFactor);
                        this.cookies.push(cookie);
                    } else if (rand >= 2 && rand <= 5) {
                        // 독수리 생성
                        let eagleY = Math.random() * (480 - 150) + 75;
                        let eagle = this.physics.add.sprite(1010, eagleY, 'eagle').setOrigin(0.5, 1);

                        let eagle_scaleFactorX = 230 / 360;
                        let eagle_scaleFactorY = 70 / 86.625;
                        eagle.setScale(eagle_scaleFactorX, eagle_scaleFactorY);
                        eagle.setSize(40 / eagle_scaleFactorX, 20 / eagle_scaleFactorY);

                        eagle.setVelocityX(Math.random() * (210 - 280 + 1) - 210 * (this.tileSpeed / 2));
                        eagle.anims.play('come');
                        this.eagles.push(eagle);
                    }

                }
            }

        }

    }

}

