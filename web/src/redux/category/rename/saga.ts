import { all, takeLatest } from 'redux-saga/effects';
import CategoryApi from '~/library/request/CategoryApi';
import { makeApiCallSagaFunc } from '~/redux/util';
import { CATEGORY_RENAME, CATEGORY_RENAME_REQUEST } from './actions';

const api = new CategoryApi();

const categoryRename$ = makeApiCallSagaFunc({
  type: CATEGORY_RENAME,
  apiFunc: api.renameCategory.bind(api),
});

export function* categoryRenameSaga() {
  yield all([takeLatest(CATEGORY_RENAME_REQUEST, categoryRename$)]);
}
