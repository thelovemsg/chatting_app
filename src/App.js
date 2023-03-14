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

import { useTranslation, Trans } from "react-i18next"; // 1. react-i18next import

const lngs = {
  // 2. 언어 구분을 위한 lng 객체 생성
  en: { nativeName: "English" },
  ko: { nativeName: "Korean" },
};

function App() {
  const { t, i18n } = useTranslation();
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
