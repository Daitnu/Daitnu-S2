import { CATEGORY_DELETE } from './actions';
import { makeApiReducer } from '~/redux/util';
import { ResponseCategory } from '~/@types/response/category';

const categoryDeleteReducer = makeApiReducer<ResponseCategory>(CATEGORY_DELETE);

export default categoryDeleteReducer;
