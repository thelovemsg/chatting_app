import "./App.css";
import "./css/style.css";
import "bootstrap/dist/css/bootstrap.min.css";
import TopContainer from "./container/TopContainer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Signin from "./component/Signin";
import Contact from "./component/Contact";
import FooterContainer from "./container/FooterContainer";
import Register from "./component/Register";
import Home from "./component/Home";
import Profile from "./component/Profile";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <TopContainer />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/signin" element={<Signin />} />
          <Route path="/register" element={<Register />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
        <FooterContainer />
      </div>
      <div id="popupDom"></div>
    </BrowserRouter>
  );
}

export default App;
