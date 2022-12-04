(ns lambdaisland.souk.sql
  (:require [clojure.string :as str]))

(defn sql-ident [v]
  (if (sequential? v)
    (str/join "." (map sql-ident v))
    (str "\""
         (if (keyword? v)
           (subs (str v) 1)
           v)
         "\"")))

(defn sql-kw [k]
  (str/upper-case
   (str/replace
    (if (keyword? k)
      (name k)
      k)
    #"-" " ")))

(defn sql-str [& ss]
  (str "'" (str/replace (apply str ss) #"'" "''") "'"))

(defn sql-list
  ([items]
   (sql-list "(" ")" ", " items))
  ([before after separator items]
   (str before (apply str (str/join separator items)) after)))

(defn strs [& items]
  (str/join " " items))

(defn sql [& items]
  (apply strs
         (map (fn [x]
                (cond
                  (vector? x)
                  (case (first x)
                    :ident (sql-ident (second x))
                    :kw (sql-kw (second x))
                    :str (sql-str (second x))
                    :raw (second x)
                    :list (sql-list (map sql (next x)))
                    :commas (sql-list "" "" ", " (map sql (next x)))
                    :fn (str (second x)
                             (sql-list (map sql (nnext x))))
                    (apply sql x))
                  (keyword? x)
                  (sql-ident x)
                  (symbol? x)
                  (sql-kw x)
                  (string? x)
                  (sql-str x)
                  (sequential? x)
                  (sql-list "(" ")" ", " (map sql x))))
              items)))
