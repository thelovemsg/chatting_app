import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguaeDetector from 'i18next-browser-languagedetector';
import en from './en.json';
import ko from './ko.json';

i18n
  .use(LanguaeDetector) // 사용자 언어 탐지
  .use(initReactI18next) // i18n 객체를 react-18next에 전달
  .init({
    // for all options read: https://www.i18next.com/overview/configuration-options
    debug: true,
    fallbackLng: 'ko',
    interpoltion: {
      escapeValue: false,
    },
    resources: {
      en,
      ko,
    },
  });

export default i18n;
