; Given a sorted list with an unsorted number V in the right-most cell, can you write some simple code to insert V into the array so it remains sorted?
;
; Print the array every time a value is shifted in the array until the array is fully sorted. The goal of this challenge is to follow the correct order of insertion sort.
;
; Input Format
; Two lines of input:
; s - the size of the array
; ar - the sorted array of integers
;
; Output Format
; On each line, output the entire array every time an item is shifted in it.
;
; Sample Input
;
; 5
; 2 4 6 8 3
; Sample Output
;
; 2 4 6 8 8
; 2 4 6 6 8
; 2 4 4 6 8
; 2 3 4 6 8

(ns solution
  (:gen-class))


(defn recursive-insertion [element index coll]
  (if (and  (not= index 0)
           (>= (nth coll (dec index)) element)
           (>= (nth coll index) element))
    (do
      (apply println (assoc coll index (nth coll (dec index))))
      (recursive-insertion element
                           (dec index)
                           (assoc coll index (nth coll (dec index)))))
    (apply println (assoc coll index element))))

(defn insertion-sort [size coll]
  (recursive-insertion (last coll)
                       (dec size)
                       (into [] (concat (drop-last coll) [(nth coll (- size 2))]))))

(defn str-to-ints
  [string]
  (map #(Integer/parseInt %)
       (.split #" " string)))

(defn -main [& args]
  (insertion-sort (first (str-to-ints (read-line)))
                  (str-to-ints (read-line))))
