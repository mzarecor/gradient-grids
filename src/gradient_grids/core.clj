(ns gradient-grids.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))


;;This chooses the frame rate and the way the program processes colors.
(defn setup []
  (q/frame-rate 1)
  (q/color-mode :rgb)
  {:color 0
   :angle 0})


;;This randomly picks colors from the entire color wheel. It is used
;;in the following draw function to create the corner values of the
;;image.
(defn rand-rgb
  []
  [(rand-int 255) (rand-int 255) (rand-int 255)])


;;A function for creating the gradient rows. The left and right end
;;values are plugged in, and it creates a linear gradient. This is
;;what creates most of the output image.
(defn row [y l r]
  (q/fill l)
  (q/rect 0 y 100 100)

  (q/fill (q/lerp-color l r 0.25))
  (q/rect 100 y 100 100)

  (q/fill (q/lerp-color l r 0.5))
  (q/rect 200 y 100 100)

  (q/fill (q/lerp-color l r 0.75))
  (q/rect 300 y 100 100)

  (q/fill r)
  (q/rect 400 y 100 100))


;;The draw function. This is a function because this block itself does
;;not run the code, it tells the execution function what to draw.
(defn draw [state]
  (q/stroke-weight 0)

  ;;Corner values which use the rand-rgb function
  (def nw (apply q/color (rand-rgb)))
  (def ne (apply q/color (rand-rgb)))
  (def sw (apply q/color (rand-rgb)))
  (def se (apply q/color (rand-rgb)))

  ;;Left and right vertical edge values, which are gradients between
  ;;the corner values generated above.
  (def l2 (q/lerp-color nw sw 0.25))
  (def l3 (q/lerp-color nw sw 0.5))
  (def l4 (q/lerp-color nw sw 0.75))
  ;;
  (def r2 (q/lerp-color ne se 0.25))
  (def r3 (q/lerp-color ne se 0.5))
  (def r4 (q/lerp-color ne se 0.75))

  ;;The application of the "row" function.
  (row 0 nw ne)
  
  (row 100 l2 r2)

  (row 200 l3 r3)

  (row 300 l4 r4)

  (row 400 sw se)

  (q/save-frame "/tmp/palettes-####.png")
  
  (q/delay-frame 2000)
  )


;;The execution function. Decides what to draw as well as other things
;;like canvas size.
(q/defsketch gradient-grids
  :size [500 500]
  :features [:keep-on-top]
  :renderer :p3d
  :middleware [m/fun-mode]
  :draw draw
  )
;;You can find the project on github at https://github.com/mzarecor/gradient-grids
