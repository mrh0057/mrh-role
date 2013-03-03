(ns mrh-roles.core)

;; Used to validate a seriosu of roles.  To make it useful in code,
;; create macros that call the validate function with the user roles.
(defn valid?
  "Used to validate a series of roles.  If they all
validate it returns true.  The validate function short circuits once of
of the roles or functions return true.  They also execute/compare from
left to right.

*user-roles*
  The current user roles.  The roles must be stored in a hash-set
*roles*
  The roles that the person has to have to validate.
EX. :admin :user (fn [] validation-func)"
  [user-roles & roles]
  (loop [roles roles
         valid true]
    (if (or (not valid)
            (empty? roles))
      valid
      (let [role (first roles)]
        (if (fn? role)
              (recur (rest roles)
                     (role))
              (recur (rest roles)
                     (contains? user-roles role)))))))
