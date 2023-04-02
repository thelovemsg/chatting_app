import React, { useState, useEffect } from "react";
import "../../css/scrollButtons.css";
import { throttle } from "./Throttling";

const ScrollButtons = () => {
  const [visible, setVisible] = useState(false);

  const checkVisibility = () => {
    const scrollTop = window.scrollY;
    const windowHeight = window.innerHeight;
    const docHeight = document.documentElement.scrollHeight;
    const buffer = 50;

    if (scrollTop + windowHeight + buffer >= docHeight) {
      setVisible(true);
    } else {
      setVisible(false);
    }
  };

  useEffect(() => {
    const throttledCheckVisibility = throttle(checkVisibility, 200);
    window.addEventListener("scroll", throttledCheckVisibility);
    return () => {
      window.removeEventListener("scroll", throttledCheckVisibility);
    };
  }, []);

  const scrollToBottom = () => {
    window.scrollTo({
      top: document.documentElement.scrollHeight,
      behavior: "smooth",
    });
  };

  return (
    visible && (
      <button className="scroll-to-bottom" onClick={scrollToBottom}>
        Scroll to Bottom
      </button>
    )
  );
};

export default ScrollButtons;
