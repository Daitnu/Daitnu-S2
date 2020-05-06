import React from 'react';
import ReactDOM from 'react-dom';
import AppRouter from './router';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import rootReducer, { rootSaga } from './redux';
import createSagaMiddleware from 'redux-saga';
import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  body {
    padding: 0;
    margin: 0;
    font-family: "Malgun Gothic", "맑은 고딕", -apple-system, BlinkMacSystemFont, helvetica, 
      "Apple SD Gothic Neo", sans-serif;
  }
`;

const sagaMiddleware = createSagaMiddleware();

const store = createStore(
  rootReducer,
  composeWithDevTools(applyMiddleware(sagaMiddleware, logger)),
);

sagaMiddleware.run(rootSaga);

ReactDOM.render(
  <Provider store={store}>
    <GlobalStyle />
    <AppRouter />
  </Provider>,
  document.getElementById('root'),
);
