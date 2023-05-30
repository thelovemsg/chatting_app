import './App.css';
import './css/style.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from 'component/nav/Signin';
import Contact from 'component/nav/Contact';
import Register from 'component/nav/Register';
import Friends from 'component/chatting/Friends';
import Home from 'component/nav/Home';
// import ScrollButtons from 'component/utilComponent/ScrollButtons';
import Navtag from 'component/nav/Navtag';
import ChattingRoomDesignTest from 'component/chatting/ChattingRoomDesignTest';
import ChattingRoom from 'component/chatting/ChattingRoom';
import FooterContainer from './container/FooterContainer';
import RoutesWrapper from './RoutesWrapper';
import LoginCheck from './component/utilComponent/LoginCheck';

const App = () => (
  <BrowserRouter>
    <Navtag />
    <RoutesWrapper>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/home" element={<Home />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/register" element={<Register />} />
        <Route path="/chattingRoom" element={<ChattingRoom />} />
        <Route path="/chattingTest" element={<ChattingRoomDesignTest />} />
        <Route path="/friends" element={<Friends />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </RoutesWrapper>
    <LoginCheck />
    {/* <ScrollButtons /> */}
    <FooterContainer />
    <div id="popupDom" />
  </BrowserRouter>
);

export default App;
