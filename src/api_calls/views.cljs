(ns api-calls.views
  (:require
   [re-frame.core :as re-frame]
   [api-calls.events :as events]
   [api-calls.subs :as subs]
   ))

(defn display-user [{:keys [id avatar email] first-name :first_name}]
  [:div.horizontal  {:key id}
   [:img.image {:src avatar}]
   [:div [:h2 first-name]
   [:p (str "(" email ")")]]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        loading (re-frame/subscribe [::subs/loading])
        users (re-frame/subscribe [::subs/users])]
    [:div
     [:h1
      "Hello from " @name]
     (when @loading "Loading ...")
     (map display-user @users)
     [:button {:on-click #(re-frame/dispatch [::events/fetch-users])} "Make API call"]
     [:button {:on-click #(re-frame/dispatch [::events/update-name " 🏄🏼‍♂️ "])} "Update Name"]
     ]))

