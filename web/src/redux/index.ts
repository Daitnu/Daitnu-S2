import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import userRegister, { userRegisterSaga } from './user/register';

const rootReducer = combineReducers({ userRegister });

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;

export function* rootSaga() {
  yield all([userRegisterSaga()]);
}