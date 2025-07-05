import React, {useState} from "react"
import {useRecoilState} from "recoil"
import {authState, cartState} from "./states/atoms"
import 'antd/dist/antd.css';
import { BrowserRouter as Router, Route,Switch } from "react-router-dom";
import Navigation from "./components/Navigation"
import CardList from "./components/CardList"
import Login from "./components/Login"
import Register from "./components/Register"
import LandingPage from "./components/LandingPage"
import Store from "./components/Store"
import CheckoutPage from "./components/CheckoutPage";
import ClothesPage from "./components/ClothesPage";
import AccesoriesPage from "./components/AccesoriesPage"
import OrderedPage from "./components/OrderedPage"

const App= ()=> {
  const [isauth, setisauth] = useRecoilState(authState)
  const [cart, setcart] = useRecoilState(cartState)
  React.useEffect(() => {
    if (localStorage.getItem("token")){
      setisauth(true)
    }
    let items =localStorage.getItem("cart");
    if( items){
      setcart(JSON.parse(items))
    }
  }, [])

  return (
      <Router>

        <Navigation />
         <Switch>
          <Route exact path="/" >
          <Store />
          {/* <CardList ip={input}/> */}
          </Route>
          <Route path="/store">
            <Store />
          </Route>

          <Route path="/orders">
            <OrderedPage />
          </Route>

          <Route path="/login">
            <Login />
          </Route>
          <Route path="/clothes">
            <ClothesPage />
          </Route>
          <Route path="/accesories">
            <AccesoriesPage />
          </Route>
          <Route path="/checkout">
            <CheckoutPage />
          </Route>

          <Route path="/register">
            <Register />
          </Route>

        </Switch> 
  </Router>
    
  );
}

export default App;
