import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import global from './global';
import user, { userSaga } from './user';
import category, { categorySaga } from './category';

const rootReducer = combineReducers({ global, user, category });

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;

export function* rootSaga() {
  yield all([userSaga(), categorySaga()]);
}
