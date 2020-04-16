import axios, { AxiosInstance } from 'axios';
import { BusinessErrorResponse } from '@customTypes/response/error';
import { HTTPResponse } from '~/@types/response/success';
import { RequestParam } from '~/@types/request/common';

const API_SERVER = 'http://localhost:8080';
const MEDIA_TYPE = {
  JSON: 'application/json',
};

const getHttpResponse = async <T>({
  fn,
  url,
  data,
}): Promise<HTTPResponse<T> | BusinessErrorResponse> => {
  try {
    const { status, data: resData } = await fn(url, data);
    const successResponse: HTTPResponse<T> = { status, data: resData };
    return successResponse;
  } catch (err) {
    if (!err.response) {
      return {
        status: 500,
        message: err.message,
        code: null,
        errors: null,
      } as BusinessErrorResponse;
    }
    const { status, message, code, errors } = err.response.data;
    const errResponse: BusinessErrorResponse = { status, message, code, errors };
    return errResponse;
  }
};

const toQueryString = (obj): string =>
  Object.entries(obj)
    .map(([k, v]) => `${k}=${v}`)
    .reduce((prev, cur) => `${prev}&${cur}`);

export class Api {
  private api: AxiosInstance;

  constructor() {
    this.api = axios.create({
      baseURL: API_SERVER,
      headers: {
        'Content-Type': MEDIA_TYPE.JSON,
        Accept: MEDIA_TYPE.JSON,
      },
      timeout: 5000,
    });
  }

  /**
   * @type T - Response Type
   * @type D - Request Type. default: undefined
   * @param - { url: string, data: D | undefined }
   */
  public async get<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    if (data !== undefined) {
      url += '?' + toQueryString(data);
      data = undefined;
    }
    return getHttpResponse<T>({ fn: this.api.get, url, data });
  }

  /**
   * @type T - Response Type
   * @type D - Request Type. default: undefined
   * @param - { url: string, data: D | undefined }
   */
  public async post<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    return getHttpResponse<T>({ fn: this.api.post, url, data });
  }

  /**
   * @type T - Response Type
   * @type D - Request Type. default: undefined
   * @param - { url: string, data: D | undefined }
   */
  public async patch<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    return getHttpResponse<T>({ fn: this.api.patch, url, data });
  }

  /**
   * @type T - Response Type
   * @type D - Request Type. default: undefined
   * @param - { url: string, data: D | undefined }
   */
  public async delete<T, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<HTTPResponse<T> | BusinessErrorResponse> {
    return getHttpResponse<T>({ fn: this.api.delete, url, data });
  }
}
