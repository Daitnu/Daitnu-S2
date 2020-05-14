import { DeleteCategoryParam } from '~/@types/request/category';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseCategory } from '~/@types/response/category';
import { BusinessErrorResponse } from '~/@types/response/error';

export const CATEGORY_DELETE = 'CATEGORY_DELETE' as const;
export const CATEGORY_DELETE_REQUEST = 'CATEGORY_DELETE_REQUEST' as const;
export const CATEGORY_DELETE_SUCCESS = 'CATEGORY_DELETE_SUCCESS' as const;
export const CATEGORY_DELETE_FAILURE = 'CATEGORY_DELETE_FAILURE' as const;

export const categoryDeleteRequest = (payload: DeleteCategoryParam) => ({
  type: CATEGORY_DELETE_REQUEST,
  payload,
});

export const categoryDeleteSuccess = (payload: HTTPResponse<ResponseCategory>) => ({
  type: CATEGORY_DELETE_SUCCESS,
  payload,
});

export const categoryDeleteFailure = (payload: BusinessErrorResponse) => ({
  type: CATEGORY_DELETE_FAILURE,
  payload,
});
