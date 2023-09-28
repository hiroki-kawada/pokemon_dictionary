# PokemonDictionary

## 使用ツール、ライブラリなど
- JetpackCompose
   - ネイティブUIをビルドする際に推奨されるAndroidの最新ツールキット。
   - 少ないコードでUIを構築できコードサイズが1/10になる場合ある。
   - 既存コードとの互換性もあり必要な場所のみ適用も可能。（今回は全てJetpackComposeでUIを構築）

- Retrofit
   - 型安全なAndroid向けのHTTPクライアントライブラリ
   - OkHttpのラッパーでありアノテーションなどを使用して実装しやすい

- Coil
   - Kotlinコルーチンを利用したAndroid用の画像読み込みライブラリ
   - KotlinファーストでありAndroid関連のライブラリを使用している
   - シンプルで画像取得処理を実装できる

- hilt
   - Android用の依存関係インジェクションライブラリ
   - プロジェクトで依存関係の注入（DI）を手動で行うためのボイラープレートが減らすことが可能

## 機能
- pokeapiを使用してポケモン図鑑の情報をNo順にリスト表示を行う。
- リスト表示したポケモン名を押下で詳細情報を表示する画面に画面遷移を行う。
- 詳細画面では選択したポケモンの「画像」「No」「名前」「高さ」「重さ」「通常特性」「夢特性」「タイプ」を表示する。


## JetpackComposeの特徴的な機能実装箇所
- NavHostを使用して画面遷移
  - このアプリは1つのActivityで複数のComposeを管理しNavHostを利用し画面遷移を実現してます。
  - 今回は1つのActivityで2つのComposeを管理しnavigateを使用して画面遷移してます。

- MaterialThemeを使用してライトモード、ダークモードに合わせて色変更

- @Composableを使用してコンポーズ可能な関数を作成しUIを構成
  - 再利用可能な小さいコンポーザブルを作成すると、アプリで使用するUI要素を簡単に構成できます。
 
## スクショ
・ライトモード：一覧画面

<img alt="ライトモード：一覧画面" src="https://github.com/hiroki-kawada/pokemon_dictionary/assets/61892987/052527ec-71ce-442d-a7d8-d381d6260e42" width="45%">

・ライトモード：詳細画面

<img alt="ライトモード：詳細画面" src="https://github.com/hiroki-kawada/pokemon_dictionary/assets/61892987/2d90452f-18c2-4ebf-b001-03e2b32e5fd5" width="45%">

・ダークモード：一覧画面

<img alt="ダークモード：一覧画面" src="https://github.com/hiroki-kawada/pokemon_dictionary/assets/61892987/2ac81ece-3c90-4e2a-9b18-5389c4657c1f" width="45%">

・ダークモード：詳細画面

<img alt="ダークモード：詳細画面" src="https://github.com/hiroki-kawada/pokemon_dictionary/assets/61892987/3239b919-a485-4aa6-89cf-b0d2993a1252" width="45%">
