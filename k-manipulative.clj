;  Suppose that A is a list of n numbers (A1, A2, A3, … , An) and
;  B (B1, B2, B3, .. ,Bn ) is a permutation of these numbers, we say B is K-Manipulative if and only if:
;
;  M(B) = min( B1 xor B2, B2 xor B3, B3 xor B4, … , Bn-1 xor Bn, Bn xor B1 ) is not less than 2^K.
;  You are given A. Find the largest K such that there exists a K-manipulative permutation B.
;
; Input:
;
; The first line is an integer N. The second line is the N integers A1 to An.
;
; Output:
; The largest possible K, or -1 if there is no solution.
;
; Constraints:
; N <= 100. Ai > 0 and can be represented in 32 bits. */
;
;
;
;  If the result needs to be so that any permutation is less than 2^K, then the K
;  last bits can always be discarded, since 2^K, is 1 followed by k 0s in binary
;
;  Then we can always look for the permutation where all elements are positioned so that
;  you never XOR a number with itself (that would equal 0, which is less than 2^K).
;
;  To ensure the aforementioned condition, one can count the number of elements and make sure
;  there are no more than n/2 repetitions of the same number, because if there are n repetitions,
;  no permutation exists so that adjacent elements are never repeated.
;
;  Solution time is N*logN + K*N
;          space is 2 * N  due to a temporary array

(ns solution
  (:gen-class))

(defn kmanipulative [totalA A]
  (defn permutation-search [bits]
    (if (empty?
          (filter #(> (second %) (/ totalA 2))
                  (reduce (fn [m x] (assoc m x (inc (m x 0)))) {}
                          (map #(bit-shift-right %1 bits) A))))
      ((println bits) (System/exit 0))
      (permutation-search (- bits 1)))
    )
  (permutation-search 31))

(defn str-to-ints
  [string]
  (map #(Integer/parseInt %)
       (.split #" " string)))

(defn -main [& args]
  (kmanipulative (first (str-to-ints (read-line)))
                 (str-to-ints (read-line))))

