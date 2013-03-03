(ns mrh-roles.test.core
  (:use [mrh-roles.core])
  (:use [clojure.test]))

(deftest valid?-test-role-only
  (is (valid? #{:me :to :you :something}
                :me :you)))

(deftest valid?-test-role-only-invalid
  (is (not (valid? #{:me :the :to}
                     :me :admin))))

(deftest valid?-test-role-fn
  (is (valid? #{:me :the :to}
                :me :the (fn [] true))))

(deftest valid?-test-role-fn-false
  (is (not (valid? #{:me :the}
                     :me :the (fn [] false)))))
