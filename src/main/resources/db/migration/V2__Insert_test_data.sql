-- 著者テーブルのテストデータ挿入
INSERT INTO authors (name, birthdate, created_at, updated_at, deleted_at) VALUES
  ('夏目 漱石', '1867-02-09', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
  ('芥川 龍之介', '1892-03-01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
  ('村上 春樹', '1949-01-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
  ('夏目 修二', '1970-07-07', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);

-- 書籍テーブルのテストデータ挿入
INSERT INTO books (title, isbn, author_id, created_at, updated_at, deleted_at) VALUES
   ('吾輩は猫である', '1234567890123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),  -- 夏目 漱石の書籍
   ('こころ', '4234567890123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),  -- 夏目 漱石の2冊目
   ('羅生門', '2234567890123', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),  -- 芥川 龍之介の書籍
   ('風の歌を聴け', '3234567890123', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),  -- 論理削除された書籍（村上 春樹）
   ('哲学とは何か', '5234567890123', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);  -- 夏目 修二の書籍

-- エラーケースのテストデータ
-- 重複したISBNを持つ書籍
-- INSERT INTO books (title, isbn, author_id, created_at, updated_at, deleted_at) VALUES
-- ('重複した書籍', '1234567890123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);  -- ISBNが重複しているのでエラー

-- 存在しない著者IDでの書籍挿入
-- INSERT INTO books (title, isbn, author_id, created_at, updated_at, deleted_at) VALUES
-- ('存在しない著者の本', '5234567890123', 99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);  -- 著者IDが存在しないのでエラー
