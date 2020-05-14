import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import add, { categoryAddSaga } from './add';
import get, { categoryGetSaga } from './get';
import del, { categoryDeleteSaga } from './delete';
import rename, { categoryRenameSaga } from './rename';

export function* categorySaga() {
  yield all([categoryAddSaga(), categoryGetSaga(), categoryDeleteSaga(), categoryRenameSaga()]);
}

export default combineReducers({
  add,
  get,
  del,
  rename,
});
