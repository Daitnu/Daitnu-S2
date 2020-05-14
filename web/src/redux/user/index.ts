import { all } from 'redux-saga/effects';
import { combineReducers } from 'redux';
import login, { userLoginSaga } from './login';
import register, { userRegisterSaga } from './register';

export function* userSaga() {
  yield all([userLoginSaga(), userRegisterSaga()]);
}

export default combineReducers({ login, register });
