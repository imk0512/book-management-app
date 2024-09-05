# 書籍管理アプリケーション

このプロジェクトは、Spring BootとKotlinを使用して構築された書籍管理APIです。ユーザーは書籍や著者を登録・検索・更新・削除できます。データはPostgreSQLに保存され、jOOQでデータベースアクセスを行っています。

## 目次

- [書籍管理アプリケーション](#書籍管理アプリケーション)
  - [目次](#目次)
  - [機能](#機能)
  - [技術スタック](#技術スタック)
  - [環境構築](#環境構築)
    - [前提条件](#前提条件)
    - [データベースのセットアップ](#データベースのセットアップ)
    - [アプリケーションのビルドと実行](#アプリケーションのビルドと実行)
  - [API ドキュメント](#api-ドキュメント)
  - [実行方法](#実行方法)

## 機能

- 書籍の登録・更新・削除
- 著者の登録・更新・削除
- 書籍や著者の検索（名前やIDで検索可能）
- 書籍と著者の論理削除
- Swagger UIによるAPIドキュメント

## 技術スタック

- 言語: Kotlin
- フレームワーク: Spring Boot
- データベース: PostgreSQL
- ORマッパー: jOOQ
- APIドキュメント: Swagger
- バージョン管理: Flyway
- ビルドツール: Gradle

## 環境構築

### 前提条件

- Java 17以上がインストールされていること
- PostgreSQLがインストールされていること
- Docker（開発用データベースをDockerで管理する場合）

### データベースのセットアップ

1. PostgreSQLの設定:
   - ホスト: `localhost`
   - ポート: `5002`
   - データベース名: `book_mng`
   - ユーザー名: `book_mng`
   - パスワード: `or3626`

2. DockerでPostgreSQLを使用する場合:

```bash
docker-compose up -d
```

3. Flywayを使ってデータベースをマイグレーション:

```bash
./gradlew flywayMigrate
```

### アプリケーションのビルドと実行

1. 依存関係のインストール:

```bash
./gradlew clean build
```

2. アプリケーションの実行:

```bash
./gradlew bootRun
```

アプリケーションは `http://localhost:8080` で実行されます。

## API ドキュメント

Swagger UIでAPIのインタラクティブなドキュメントを確認できます。

- URL: `http://localhost:8080/swagger-ui.html`

## 実行方法

1. PostgreSQLをセットアップします（またはDockerを使用）。
2. Flywayを使ってデータベースマイグレーションを実行します。
3. アプリケーションをビルドし、起動します。
4. Swagger UIでAPIを確認できます。
