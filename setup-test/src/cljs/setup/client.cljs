(ns setup.client)

(enable-console-print!)

(def request-animation-frame (or (.-requestAnimationFrame js/window)
                               (.-mozRequestAnimationFrame js/window)
                               (.-webkitRequestAnimationFrame js/window)
                               (.-msRequestAnimationFrame js/window)
                               (fn [f] (.setTimeout js/window f 16))))

(def canvas (.getElementById js/document "c"))
(def ctx    (.getContext canvas "2d"))
(def state  (atom nil))

(defn run []
  (request-animation-frame run)
  (let [width  (.-innerWidth js/window)
        height (.-innerHeight js/window)
        {:keys [tick image]} (swap! state update-in [:tick] inc)]
    (doto canvas
      (aset "width" width)
      (aset "height" height))
    (doto ctx
      (aset "fillStyle" "rgb(255,255,255)")
      (.fillRect 0 0 width height)
      (.save)
      (aset "textAlign" "center")
      (aset "textBaseline" "top")
      (aset "font" "18px sans-serif")
      (aset "fillStyle" "rgb(92,92,92)")
      (.fillText "You rock!  Your setup is perfect." (/ width 2) 12)
      (.restore)
      (.save)
      (.translate (/ width 2) (/ height 2))
      (.rotate (* tick 0.005))
      (.drawImage image (/ (.-width image) -2) (/ (.-height image) -2))
      (.restore))))

(defn start []
  (println "ready")
  (let [image (new js/Image)]
    (reset! state {:tick 0, :image image})
    (doto image
      (aset "onload" run)
      (aset "src" "logo.png"))))

(-> js/window .-onload (set! start))
