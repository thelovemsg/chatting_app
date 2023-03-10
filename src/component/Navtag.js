import { useEffect } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { useSelector } from "react-redux";
import { Link, Navigate, useNavigate } from "react-router-dom";
import Login from "./Login";
import Logout from "./Logout";

const Navtag = () => {
  const navigate = useNavigate();
  const { loginDone, logoutDone } = useSelector((state) => state.user);
  useEffect(() => {
    if (logoutDone) navigate("/home");
    if (loginDone) navigate("/home");
  }, [logoutDone, loginDone]);

  return (
    <Navbar bg="dark" expand="lg" className="color-yellow-first">
      <Container>
        <Navbar.Brand className="color-yellow-first">
          <Link to="/home" className="color-yellow-first href-style">
            각카호 채팅
          </Link>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse
          id="basic-navbar-nav"
          className="justify-content-end color-yellow-first"
        >
          <Nav className="me-autod color-yellow-first">
            <Nav.Link>
              {loginDone ? (
                <Logout />
              ) : (
                <Link to="/signin" className="color-yellow-first href-style">
                  <Login />
                </Link>
              )}
            </Nav.Link>
            <Nav.Link>
              <Link to="/register" className="color-yellow-first href-style">
                회원가입
              </Link>
            </Nav.Link>
            <Nav.Link>
              <Link to="/profile" className="color-yellow-first href-style">
                로그인 시 나의 정보 표출
              </Link>
            </Nav.Link>
            <NavDropdown
              title="Dropdown"
              id="basic-nav-dropdown"
              className="color-yellow-first"
            >
              <NavDropdown.Item>
                <Link to="explain" className="href-style">
                  설명
                </Link>
              </NavDropdown.Item>
              <NavDropdown.Item>
                <Link to="/meeting" className="href-style">
                  사용법
                </Link>
              </NavDropdown.Item>
              <NavDropdown.Item>
                <Link to="/contact" className="href-style">
                  contact us
                </Link>
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Navtag;
