import { CATEGORY_GET } from './actions';
import { makeApiReducer } from '~/redux/util';
import { ResponseCategory } from '~/@types/response/category';

const categoryGetReducer = makeApiReducer<ResponseCategory>(CATEGORY_GET);

export default categoryGetReducer;
