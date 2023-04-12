import './App.css';
import './css/style.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import TopContainer from './container/TopContainer';
import Signin from './component/Signin';
import Contact from './component/Contact';
import FooterContainer from './container/FooterContainer';
import Register from './component/Register';
import Home from './component/Home';

// import ChattingRoom from "./component/main/ChattingRoom";
import Friends from './component/chatting/Friends';
import ScrollButtons from './component/utilComponent/ScrollButtons';
import Navtag from './component/Navtag';

const App = () => (
  <BrowserRouter>
    <div>
      <Navtag />
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
    <div id="popupDom" />
  </BrowserRouter>
);

export default App;
