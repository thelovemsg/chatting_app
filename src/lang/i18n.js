import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import LanguaeDetector from "i18next-browser-languagedetector";

i18n
  .use(LanguaeDetector) // 사용자 언어 탐지
  .use(initReactI18next) // i18n 객체를 react-18next에 전달
  .init({
    // for all options read: https://www.i18next.com/overview/configuration-options
    debug: true,
    fallbackLng: "ko",
    interpolation: {
      escapeValue: false,
    },
    resources: {
      en: {
        translation: {
          // 번역본 쓸 공간
          description: {
            part1: "Edit src/App.js and save to reload.",
            part2: "Learn React",
          },
          carousel: {
            carousel_image_1_title: "First",
            carousel_image_1_description:
              "Hello! Welcome To MSG's Mini Project!",
            carousel_image_2_title: "Second",
            carousel_image_2_description:
              "I'd like to make a variety of things here!",
            carousel_image_3_title: "Third",
            carousel_image_3_description:
              "If you don't like it, just give me any feedback!",
          },
        },
      },
      ko: {
        translation: {
          // 번역본 쓸 공간
          description: {
            part1: "src/App.js를 편집하고 저장하여 다시 로드합니다.",
            part2: "React 배우러가기",
          },
          carousel: {
            carousel_image_1_title: "첫번째",
            carousel_image_1_description:
              "안녕하세요! MSG의 미니 프로젝트에 오신 것을 환영합니다!",
            carousel_image_2_title: "두번째",
            carousel_image_2_description: "이곳에서 많은 것을 해보고 싶어요!",
            carousel_image_3_title: "세번째",
            carousel_image_3_description:
              "마음에 안드신다면, 어떤 피드백이든 주세요!",
          },
        },
      },
    },
  });

export default i18n;
