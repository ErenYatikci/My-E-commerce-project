import React from 'react'
import Checkout_ITEM from "./CheckoutItem"
import {Input} from "antd"
import axios from "axios"
import putNotification from "./Notification"

const LandingPage = () => {
  const [email, setEmail] = React.useState("")

  const send = async () => {
      try {
        putNotification("Subscribing...")
        const resp = await axios.post("https://localhost:8443/api/v1/newsletter", {email:email})

        if (resp.status===200){
          putNotification(resp.data)
          setEmail("")
        }
      } catch (error) {
        putNotification("!!Error!!", error.response?.data?.message)
      }


  }
    return (
<div class="jumbotron" style={{minHeight:"100vh"}}>
  <h1 class="display-3" style={{textAlign:"center"}}>My Ecommer project</h1>
  <p class="lead" style={{textAlign:"center", paddingTop:0}}>Deneme123</p>
  <h1 class="display-4">Welcome to my project</h1>
  <hr class="my-4" />
  {/* <Checkout_ITEM /> */}
  <h6>Check out out best deals...</h6>
  <p class="lead">
    <a class="ant-btn ant-btn-primary ant-btn-lg" style={{borderRadius:20}} href="/store" >Enter Into Store</a>
  </p>
</div>

    )
}

export default LandingPage
