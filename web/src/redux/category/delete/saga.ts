import { all, takeLatest } from 'redux-saga/effects';
import CategoryApi from '~/library/request/CategoryApi';
import { makeApiCallSagaFunc } from '~/redux/util';
import { CATEGORY_DELETE, CATEGORY_DELETE_REQUEST } from './actions';

const api = new CategoryApi();

const categoryDelete$ = makeApiCallSagaFunc({
  type: CATEGORY_DELETE,
  apiFunc: api.deleteCategory.bind(api),
});

export function* categoryDeleteSaga() {
  yield all([takeLatest(CATEGORY_DELETE_REQUEST, categoryDelete$)]);
}
