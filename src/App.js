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

// import ChattingRoom from "./component/main/ChattingRoom";
import Friends from "./component/chatting/Friends";
import ScrollButtons from "./component/utility/ScrollButtons";

function App() {
  return (
    <BrowserRouter>
      <div>
        <TopContainer />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/signin" element={<Signin />} />
          <Route path="/register" element={<Register />} />
          <Route path="/friends" element={<Friends />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
        <ScrollButtons />
        <FooterContainer />
      </div>
      <div id="popupDom"></div>
    </BrowserRouter>
  );
}

export default App;
