// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCkP5T1MV37qYoO8ovcUpNQTyp0JucRY64",
  authDomain: "remp-e7e85.firebaseapp.com",
  projectId: "remp-e7e85",
  storageBucket: "remp-e7e85.appspot.com",
  messagingSenderId: "337409210695",
  appId: "1:337409210695:web:9a18b28c6fff3bf6fbf462",
  measurementId: "G-V6YSWZQR3Q"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export default app;
export const db = getFirestore(app);
