package blackjack2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class BlackJackApp extends Application {

	private static Canvas canvas;// 640*480pxのCanvasインスタンスの生成
	private static GraphicsContext gc;
	public static Pane pane;// Paneインスタンスの生成
	public static MediaView view;

	public static GraphicsContext getGC() {
		return gc;
	}

	@Override
	public void start(Stage primaryStage) {

		pane = new Pane();

		canvas = new Canvas(720, 480);

		pane.getChildren().add(canvas);// canvasをboardにadd
		Scene scene = new Scene(pane);// シーンインスタンスの生成

		gc = canvas.getGraphicsContext2D();

		//暗黙的なプログラムの終了？ Platform.exit()を使わない場合
		Platform.setImplicitExit(true);

		// ウィンドウが閉じられた時のイベントを設定
		// ゲームのデータを保存するような場合はこの設計は正さなければならない
		primaryStage.setOnCloseRequest(e -> System.exit(0));

		primaryStage.setScene(scene);
		primaryStage.show();

		Thread game = new Thread(){
			@Override
			public void run() {

			}
		};
		new Thread(game).start();// ゲームスレッドの開始

	}

	public static void main(String[] args) {
		launch(args);
	}

}
