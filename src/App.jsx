import './App.css';
import './css/style.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from 'component/nav/Signin';
import Contact from 'component/nav/Contact';
import Register from 'component/nav/Register';
import Home from 'component/nav/Home';

import Friends from 'component/chatting/Friends';
import ScrollButtons from 'component/utilComponent/ScrollButtons';
import Navtag from 'component/nav/Navtag';
import ChattingRoom from 'component/chatting/ChattingRoom';
import FooterContainer from './container/FooterContainer';

const App = () => (
  <BrowserRouter>
    <div>
      <Navtag />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/home" element={<Home />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/register" element={<Register />} />
        <Route path="/chattingTest" element={<ChattingRoom />} />
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
