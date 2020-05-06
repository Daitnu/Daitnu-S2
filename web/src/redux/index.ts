import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import userRegister, { userRegisterSaga } from './user/register';
import userLogin, { userLoginSaga } from './user/login';

const rootReducer = combineReducers({ userRegister, userLogin });

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;

export function* rootSaga() {
  yield all([userRegisterSaga(), userLoginSaga()]);
}
