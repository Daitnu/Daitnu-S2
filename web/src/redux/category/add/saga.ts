import { all, takeLatest } from 'redux-saga/effects';
import CategoryApi from '~/library/request/CategoryApi';
import { makeApiCallSagaFunc } from '~/redux/util';
import { CATEGORY_ADD, CATEGORY_ADD_REQUEST } from './actions';

const api = new CategoryApi();

const categoryAdd$ = makeApiCallSagaFunc({
  type: CATEGORY_ADD,
  apiFunc: api.addCategory.bind(api),
});

export function* categoryAddSaga() {
  yield all([takeLatest(CATEGORY_ADD_REQUEST, categoryAdd$)]);
}
