import { useCallback, useEffect } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import Login from "./Login";
import Logout from "./Logout";
import { useTranslation, Trans } from "react-i18next"; // 1. react-i18next import
import { StyledLangButton } from "../styled-components/StyledForm";
import { useState } from "react";
import classNames from "classnames";

const Navtag = () => {
  const navigate = useNavigate();
  const { loginDone, logoutDone } = useSelector((state) => state.user);
  const [activeLink, setActiveLink] = useState("");

  useEffect(() => {
    if (logoutDone || loginDone) navigate("/home");
  }, [logoutDone, loginDone]);

  const lngs = {
    // 2. 언어 구분을 위한 lng 객체 생성
    en: { nativeName: "English" },
    ko: { nativeName: "Korean" },
  };

  const handleLinkClick = (path) => {
    setActiveLink(path);
    navigate(path);
  };

  const generateLinkClassName = (path) => {
    return classNames("color-yellow-first", "href-style", {
      "active-link": activeLink === path,
    });
  };

  const { t, i18n } = useTranslation();

  return (
    <div id="header">
      <Navbar bg="dark" expand="lg" className="color-yellow-first">
        <Container>
          <Navbar.Brand className="color-yellow-first">
            <Link to="/home" className="color-yellow-first href-style">
              각카호 채팅
            </Link>
            <Link>
              {Object.keys(lngs).map((lng) => (
                <StyledLangButton
                  key={lng}
                  style={{
                    fontWeight:
                      i18n.resolvedLanguage === lng ? "bold" : "normal",
                  }}
                  variant="secondary"
                  onClick={() => i18n.changeLanguage(lng)}
                >
                  {" "}
                  {lngs[lng].nativeName}
                </StyledLangButton>
              ))}
            </Link>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse
            id="basic-navbar-nav"
            className="justify-content-end color-yellow-first"
          >
            <Nav className="color-yellow-first">
              <Nav.Link className="href-style">
                <Link
                  to="/home"
                  className={generateLinkClassName("/home")}
                  onClick={() => handleLinkClick("/home")}
                >
                  홈화면
                </Link>
              </Nav.Link>
              {loginDone ? (
                <>
                  <Nav.Link className="href-style">
                    <Link
                      to="/friends"
                      className={generateLinkClassName("/friends")}
                      onClick={() => handleLinkClick("/friends")}
                    >
                      채팅하기
                      {/* 채팅하기는 로그인 해야만 되도록 하자.
                  만일 로그인이 안됫으면? 로그인 화면으로 안내한 후에 다시 채팅방으로 와야함. */}
                    </Link>
                  </Nav.Link>
                  <Nav.Link className="href-style">
                    <Link
                      to="/profile"
                      className={generateLinkClassName("/profile")}
                      onClick={() => handleLinkClick("/profile")}
                    >
                      프로필
                      {/* 프로필도 로그인 한 후에 표출 */}
                    </Link>
                  </Nav.Link>
                  <Nav.Link>
                    <Logout />
                  </Nav.Link>
                </>
              ) : (
                <Nav.Link>
                  <Link
                    to="/signin"
                    className={generateLinkClassName("/signin")}
                    onClick={() => handleLinkClick("/signin")}
                  >
                    <Login />
                  </Link>
                </Nav.Link>
              )}
              <Nav.Link className="href-style">
                <Link
                  to="/register"
                  className={generateLinkClassName("/register")}
                  onClick={() => handleLinkClick("/register")}
                >
                  회원가입
                </Link>
              </Nav.Link>
              <NavDropdown
                title="Dropdown"
                id="basic-nav-dropdown"
                className="color-yellow-first href-style"
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
    </div>
  );
};

export default Navtag;
