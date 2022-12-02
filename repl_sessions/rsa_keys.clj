(ns repl-sessions.rsa-keys
  (:require
   [clojure.string :as str])
  (:import
   (java.security KeyPairGenerator Signature)
   (java.security.spec X509EncodedKeySpec)
   (java.util Base64)))

(def kpg (KeyPairGenerator/getInstance "RSA"))

(.initialize kpg 2048)
(def kp (.generateKeyPair kpg))

(.getEncoded (.getPublic kp))
(.getEncoded (.getPrivate kp))

(let [s (.encodeToString (Base64/getEncoder) (.getEncoded (.getPublic kp)))
      parts (map (partial apply str) (partition-all 64 s))]
  (str/join
   (map #(str % "\r\n")
        `["-----BEGIN PUBLIC KEY-----"
          ~@parts
          "-----END PUBLIC KEY-----"])))

(def pem "-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy3WsUuEyZLsy/2XxJ+ou\r\nnNr14R1x9laQh4EitjT4e1OPJwHHIBqEPUWk4MQzU13Jga4uua28Ecl3BxC9lSnf\r\nDp96Z0NAdkYjuCgC9xo9EjKaK8ijIbm58d4uifIl/XKZE6tYTGXXzmnx4nCfcWfF\r\n67tut/4k+/wVMjjHMLl9VhzHsBz3Wr+h7v+4SLFftq9NorMknWQuIh3IzQUNZBps\r\nCw8JRDUx8Of/I44mJMc2N12f41TLK65VCvkXF3K5qIS9jTEdhhOA8dsB92DEyaTu\r\ns+jhqXM4ivFfxDyOasQRZ0bEO+OEcJua7nnvNsFzGLkIb3/eJ1HlCQ+AKVSUGcBZ\r\nbwIDAQAB\r\n-----END PUBLIC KEY-----\r\n")



(X509EncodedKeySpec.
 (.decode (Base64/getDecoder)
          (str/replace pem #"(-+(BEGIN|END) PUBLIC KEY-+|\R)" ""))
 )

;; sign
(def sign (Signature/getInstance "SHA256withRSA"))
(.initSign sign (.getPrivate kp))
(.update sign (.getBytes "hello"))
(def signature (.sign sign))

(.encodeToString (Base64/getEncoder) signature)

;; verify
(def sign (Signature/getInstance "SHA256withRSA"))
(.initVerify sign (.getPublic kp))

(.update sign (.getBytes "hello"))
(.verify sign signature)
