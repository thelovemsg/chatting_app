import "./App.css";
import "./css/style.css";
import "bootstrap/dist/css/bootstrap.min.css";
import TopContainer from "./container/TopContainer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Signin from "./component/Signin";
import Meeting from "./component/Meeting";
import Contact from "./component/Contact";
import FooterContainer from "./container/FooterContainer";
import Register from "./component/Register";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <TopContainer />
        <Routes>
          <Route path="/signin" element={<Signin />} />
          <Route path="/register" element={<Register />} />
          <Route path="/profile" element={<Meeting />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
        <FooterContainer />
      </div>
    </BrowserRouter>
  );
}

export default App;
