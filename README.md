# Galaxy CamSnd

純正カメラアプリのシャッター音をオフにします

> [!NOTE]
> One UI 6.0 以降では、｢お使いのスマートフォンに対応していないため...｣と表示され、パッケージインストーラーからアプリをインストールする事が出来なくなりました。  
> 解決策を模索しているので、それまでは、ADBによるインストールをお願いします。
> ```
> adb install --bypass-low-target-sdk-block GalaxyCamSnd.apk
> ```
> ︎
> [**Shizuku**](https://github.com/RikkaApps/Shizuku) を介して行う場合は以下のコマンドでインストール出来ます。
> ```
> pm install −−bypass-low-target-sdk-block GalaxyCamSnd.apk
> ```
> ただし、`/data/local/tmp`等にコピーしないとインストール出来ない場合があります。

## インストール方法

1. [**ここ**](https://github.com/s1204IT/GalaxyCamSnd/releases/latest/)からアプリファイル(APK)をダウンロードする
2. ダウンロード後、｢**開く**｣を押す
  > [!TIP]
  > ｢セキュリティ上の理由から...｣と表示された場合は、  
  > ｢設定｣を押して、ダウンロードに使用したブラウザのトグルボタンをオンにしてください
3. ｢**インストール**｣または｢**更新**｣を押し、完了後、｢**開く**｣

### 無音化解除 / アンインストール方法
設定アプリ  
→アプリ  
→Galaxy CamSnd  
→アプリ内の通知設定  

---

## 検証端末
- Galaxy S22 One UI 6.0 ⭕
- Galaxy S22 One UI 5.1 ⭕
- Galaxy S20 5G ⭕
- Galaxy S9+ :warning:  
  トーストメッセージ非表示
- Galaxy S8 :x:
